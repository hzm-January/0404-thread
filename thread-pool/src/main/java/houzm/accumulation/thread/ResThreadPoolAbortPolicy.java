/*
 * @(#) ResCustomThreadPool.java
 * @Author:houzm(mail) 2018年06月19日
 * @Copyright (c) 2002-2017 9air.com Limited. All rights reserved.
 */
package houzm.accumulation.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author houzm 2018年08月05日
 * @version 1.0
 * @Function 线程池
 */
public final class ResThreadPoolAbortPolicy extends ThreadPoolExecutor {
    private Logger logger = LoggerFactory.getLogger(ResThreadPoolAbortPolicy.class);
    private static volatile ResThreadPoolAbortPolicy instance; //确保可见性
    private final AtomicLong totalTask = new AtomicLong(0);
    private final AtomicLong totalTime = new AtomicLong(0);
    private final ThreadLocal<Long> startTime = new ThreadLocal<>();
    private ResThreadPoolAbortPolicy(int corePoolSize, int maximumPoolSize,
                                     long keepAliveTime, TimeUnit unit,
                                     BlockingQueue<Runnable> workQueue,
                                     ThreadFactory threadFactory,
                                     RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
        if (instance != null) {
            throw new IllegalStateException("RES CUSTOM THREAD POOL ALREADY INITIALIZED.");
        }
    }

    /**
     * get instance
     *
     * @return
     */
    public static ResThreadPoolAbortPolicy getInstance() {
        // 局部变量可以使性能提高25%，确保resCustomThreadPool只在已经被初始化的情况下读取一次
        ResThreadPoolAbortPolicy result = instance;
        if (result == null) {
            synchronized (ResThreadPoolAbortPolicy.class) {
                // 校验是否已经有其他线程进行了初始化
                result = instance;
                if (result == null) {
                    instance = new ResThreadPoolAbortPolicy(Runtime.getRuntime().availableProcessors() + 1, 200,
                            1, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(400),
                            resCustomThreadFactory(), resCustomCallerRunsPolicy());
                    result = instance;
                }
            }
        }
        return result;
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        logger.info("THREAD POOL EXECUTE START");
        startTime.set(System.nanoTime());
        super.beforeExecute(t, r);
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        logger.info("THREAD POOL EXECUTE END");
        Long startTimeLong = startTime.get();
        long totalTimeOfTheTask = System.nanoTime() - startTimeLong;
        totalTime.addAndGet(totalTimeOfTheTask);
        totalTask.incrementAndGet();
        startTime.remove();
//        super.afterExecute(r, t);
        String lineSeparator = System.lineSeparator();
        StringBuffer info = new StringBuffer(100);
//        info.append(lineSeparator+"===================================================="+lineSeparator);
//        info.append("totalTime: "+ totalTime +"-----totalTask: "+ totalTask +lineSeparator);
//        info.append("totalTime/totalTask ："+ totalTime.doubleValue()/ totalTask.doubleValue()+lineSeparator);
//        info.append("核心线程数："+instance.getCorePoolSize()+lineSeparator);
//        info.append("活动线程数："+instance.getActiveCount()+lineSeparator);
//        info.append("排队线程数："+instance.getQueue().size()+lineSeparator);
//        info.append("线程数峰值："+instance.getLargestPoolSize()+lineSeparator);
//        info.append("设置的最大线程数："+instance.getMaximumPoolSize()+lineSeparator);
//        info.append("线程池中线程数："+instance.getPoolSize()+lineSeparator);
//        info.append("任务总数："+instance.getTaskCount()+lineSeparator);
//        info.append("任务完成数："+instance.getCompletedTaskCount()+lineSeparator);
//        info.append("===================================================="+lineSeparator);
        logger.info(info.toString());
    }

    @Override
    protected void terminated() {
//        super.terminated();
        String lineSeparator = System.lineSeparator();
        StringBuffer info = new StringBuffer(100);
//        info.append(lineSeparator+"===================================================="+lineSeparator);
//        info.append("totalTime: "+ totalTime +"-----totalTask: "+ totalTask +lineSeparator);
//        info.append("totalTime/totalTask ："+ totalTime.doubleValue()/ totalTask.doubleValue()+lineSeparator);
//        info.append("核心线程数："+instance.getCorePoolSize()+lineSeparator);
//        info.append("活动线程数："+instance.getActiveCount()+lineSeparator);
//        info.append("线程数峰值："+instance.getLargestPoolSize()+lineSeparator);
//        info.append("设置的最大线程数："+instance.getMaximumPoolSize()+lineSeparator);
//        info.append("线程池中线程数："+instance.getPoolSize()+lineSeparator);
//        info.append("任务总数："+instance.getTaskCount()+lineSeparator);
//        info.append("任务完成数："+instance.getCompletedTaskCount()+lineSeparator);
//        info.append("===================================================="+lineSeparator);
        logger.info(info.toString());
    }

    @Override
    public void shutdown() {
        //禁止shutdown，池无需关闭
        logger.info(System.lineSeparator()+"THREAD POOL DOES NOT NEED TO BE CLOSED");
    }

    private static ThreadFactory resCustomThreadFactory() {
        return new ResCustomThreadFactory();
    }

    private static RejectedExecutionHandler resCustomCallerRunsPolicy(){
            return new ResCustomCallerRunsPolicy();
    }

    /**
     * The thread factory of res custom
     */
    static class ResCustomThreadFactory implements ThreadFactory {

        private static final AtomicInteger POOL_NUMBER = new AtomicInteger(1);
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        ResCustomThreadFactory() {
            SecurityManager s = System.getSecurityManager();
            if (s != null) {
                group = s.getThreadGroup();
            } else {
                group = Thread.currentThread().getThreadGroup();
            }
            namePrefix = "pool-" + POOL_NUMBER.getAndIncrement() + "-thread-";
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r,
                    namePrefix + threadNumber.getAndIncrement(),
                    0);
            if (t.isDaemon()) {
                t.setDaemon(false);
            }
            if (t.getPriority() != Thread.NORM_PRIORITY) {
                t.setPriority(Thread.NORM_PRIORITY);
            }
            return t;
        }
    }

    /**
     * A handler for rejected tasks that runs the rejected task
     * directly in the calling thread of the {@code execute} method,
     * unless the executor has been shut down, in which case the task
     * is discarded.
     * 当线程池的任务缓存队列已满并且线程池中的线程数目达到maximumPoolSize，
     * 如果还有任务到来就会采取任务拒绝策略，
     * 丢弃任务并抛出RejectedExecutionException异常
     */
    public static class ResCustomCallerRunsPolicy implements RejectedExecutionHandler {
        /**
         * Creates an {@code AbortPolicy}.
         */
        public ResCustomCallerRunsPolicy() { }

        /**
         * Always throws RejectedExecutionException.
         *
         * @param r the runnable task requested to be executed
         * @param e the executor attempting to execute this task
         * @throws RejectedExecutionException always
         */
        public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
            throw new RejectedExecutionException("Task " + r.toString()
                    + " rejected from "
                    + e.toString());
        }
    }
}
