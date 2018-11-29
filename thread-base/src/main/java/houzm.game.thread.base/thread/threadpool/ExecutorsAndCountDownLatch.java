package houzm.game.thread.base.thread.threadpool;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Package: houzm.game.thread.base.thread.threadpool
 * Author: houzm
 * Date: Created in 2018/6/25 21:27
 * Copyright: Copyright (c) 2018
 * Version: 0.0.1
 * Modified By:
 * Description： Executors 类下的线程池
 */
public class ExecutorsAndCountDownLatch {
    public static void main(String[] args) {

        ExecutorService executorService = Executors.newCachedThreadPool();
        final CountDownLatch countDownLatch = new CountDownLatch(2);
        Future<Integer> future2 = executorService.submit(new Callable<Integer>() {
            public Integer call() throws Exception {
                System.out.println("=====正常Integer转换=====");
                Integer integer = Integer.valueOf("12");
                countDownLatch.countDown();
                return integer;
            }
        });
        Future<Integer> future = executorService.submit(new Callable<Integer>() {
            public Integer call() throws Exception {
                System.out.println("=====不正常Integer转换=====");
                Integer integer = Integer.valueOf("houzhiming");
                countDownLatch.countDown();
                return integer;
            }
        });

        try {
            System.out.println("====阻塞主线程====");
            countDownLatch.await(5, TimeUnit.SECONDS);
//            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("====主线程继续执行====");
        try {
            System.out.println("future.isCancelled():"+future.isCancelled());
            System.out.println("future.isDone():"+future.isDone());
//            System.out.println("future.get():"+future.get(1, TimeUnit.SECONDS));
//            System.out.println("future.get():"+future.get());
            if (!future.isCancelled() && future.isDone()) {
                System.out.println("------------");
                System.out.println("future.get():"+future.get(1, TimeUnit.SECONDS));
            }
            System.out.println("future2.isCancelled():"+future2.isCancelled());
            System.out.println("future2.isDone():"+future2.isDone());
            System.out.println("future2.get():"+future2.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
