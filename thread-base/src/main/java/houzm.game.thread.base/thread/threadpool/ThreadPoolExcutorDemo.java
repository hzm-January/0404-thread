package houzm.game.thread.base.thread.threadpool;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/**
 * Package: houzm.game.thread.base.thread.threadpool
 * Author: houzm
 * Date: Created in 2018/7/17 17:11
 * Copyright: Copyright (c) 2018
 * Version: 0.0.1
 * Modified By:
 * Description： TODO
 */
public class ThreadPoolExcutorDemo {
    private static volatile int count = 0;
    public static void main(String[] args) throws InterruptedException {

        //Runtime.getRuntime().availableProcessors()+1
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 400,
                50, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(100000));
//        threadPoolExecutor.execute(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println(++count);
//            }
//        });
        Lock lock = new ReentrantLock();
        IntStream.rangeClosed(1, 100000).forEach(key->{
            Future<Integer> submit = threadPoolExecutor.submit(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    Thread.sleep(5);
                    lock.lock();
                    System.out.println(Thread.currentThread().getName()+" "+(++count));
                    lock.unlock();
                    return count;
                }
            });
//            System.out.println("执行结果："+submit);
        });
        Boolean flag = true;
        while (flag) {
            System.out.println("核心线程数："+threadPoolExecutor.getCorePoolSize());
            System.out.println("最大线程数："+threadPoolExecutor.getMaximumPoolSize());
            System.out.println("峰值线程数："+threadPoolExecutor.getLargestPoolSize());
            System.out.println("活动线程数："+threadPoolExecutor.getActiveCount());
            System.out.println("总任务数："+threadPoolExecutor.getTaskCount());
            System.out.println("排队任务数："+threadPoolExecutor.getQueue().size());
            System.out.println("任务完成数："+threadPoolExecutor.getCompletedTaskCount());
            Thread.sleep(1000);
            if (threadPoolExecutor.getQueue().size() <= 0) {
                flag = false;
            }
        }

        threadPoolExecutor.shutdown();
    }
}
