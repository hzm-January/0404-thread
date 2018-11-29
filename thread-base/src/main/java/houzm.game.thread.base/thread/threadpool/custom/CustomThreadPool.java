package houzm.game.thread.base.thread.threadpool.custom;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Package: houzm.game.thread.base.thread.threadpool.custom
 * Author: houzm
 * Date: Created in 2018/7/21 17:26
 * Copyright: Copyright (c) 2018
 * Version: 0.0.1
 * Modified By:
 * Description： 自定义线程池
 */
public class CustomThreadPool extends ThreadPoolExecutor {

//    private static final Logger logger = LoggerFactory.getLogger(CustomThreadPool.class);

    private static volatile CustomThreadPool customThreadPool;

    private final ThreadLocal<Long> startTime = new ThreadLocal<Long>();
    private final AtomicLong totalTime = new AtomicLong(0);
    private final AtomicLong totalTask = new AtomicLong(0);

    private CustomThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
                             BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
        if (customThreadPool != null) {
            //防止反射调用
            throw new IllegalStateException("ALREADY INITIALIZED");
        }
    }

    public static CustomThreadPool getCustomThreadPool() {
        CustomThreadPool result = customThreadPool;
        if (result == null) {
            synchronized (CustomThreadPool.class) {
                result = customThreadPool;
                if (result == null) {
                    customThreadPool = result = new CustomThreadPool(Runtime.getRuntime().availableProcessors() + 1,
                            100, 1, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(200),
                            customThreadFactory(), new CallerRunsPolicy());
                }
            }
        }
        return result;
    }

    private static CustomThreadFactory customThreadFactory() {
        return new CustomThreadFactory();
    }

    static class CustomThreadFactory implements ThreadFactory {

        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        CustomThreadFactory() {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() :
                    Thread.currentThread().getThreadGroup();
            namePrefix = "pool-" +
                    poolNumber.getAndIncrement() +
                    "-thread-";
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r,
                    namePrefix + threadNumber.getAndIncrement(),
                    0);
            if (t.isDaemon())
                t.setDaemon(false);
            if (t.getPriority() != Thread.NORM_PRIORITY)
                t.setPriority(Thread.NORM_PRIORITY);
            return t;
        }
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        super.beforeExecute(t, r);
//        System.out.println("THREAD : group-"+t.getThreadGroup().getName()+" name-"+t.getName()+" start ");
        System.out.println("THREAD :  name-"+t.getName()+" start ");
//        System.out.println("THREAD : group-"+t.getThreadGroup().getName()+" name-"+t.getName()+" start "+r);
        startTime.set(System.nanoTime());
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        super.afterExecute(r, t);
        long timeOfExecute = System.nanoTime() - startTime.get();
        System.out.println("THREAD :  name-"+Thread.currentThread().getName()+"---"+ timeOfExecute);
        totalTime.addAndGet(timeOfExecute); //记录所有任务总执行时间
        totalTask.incrementAndGet(); //记录任务数
    }

    @Override
    protected void terminated() {
        super.terminated();
        System.out.println("==============================================");
        System.out.println("totalTime: "+totalTime +"-----totalTask: "+totalTask);
        System.out.println("totalTime/totalTask ："+totalTime.doubleValue()/totalTask.doubleValue());


        System.out.println("核心线程数："+customThreadPool.getCorePoolSize());
        System.out.println("活动线程数："+customThreadPool.getActiveCount());
        System.out.println("线程数峰值："+customThreadPool.getLargestPoolSize());
        System.out.println("设置的最大线程数："+customThreadPool.getMaximumPoolSize());
        System.out.println("线程池中线程数："+customThreadPool.getPoolSize());
        System.out.println("任务总数："+customThreadPool.getTaskCount());
        System.out.println("任务完成数："+customThreadPool.getCompletedTaskCount());
    }


}
