package houzm.game.thread.base.countdownlatch;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Package: houzm.game.thread.base.countdownlatch
 * Author: houzm
 * Date: Created in 2018/8/6 12:21
 * Copyright: Copyright (c) 2018
 * Version: 0.0.1
 * Modified By:
 * Description： TODO
 */
public class TestBlockingOfCounDownLatch {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        CountDownLatch countDownLatch = new CountDownLatch(2);
        Future<Integer> future = executorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {

                if (true) {
                    Thread.sleep(5000);
                    throw new IllegalStateException("error");
//
                }
//                countDownLatch.countDown();
                return 12;
            }
        });
        Future<Integer> future2 = executorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                countDownLatch.countDown();
                return 11;
            }
        });
        try {
//            countDownLatch.await();
            boolean await = countDownLatch.await(2, TimeUnit.SECONDS);
            if (await == false) {
                System.out.println("await false");
                 System.out.println(future.get());
                System.out.println(future2.get());
                System.out.println(countDownLatch.getCount());
            } else {
                System.out.println(future.get());
                System.out.println(future2.get());
                System.out.println("===============");
                System.out.println(future.get());
                System.out.println(future2.get());
                System.out.println(countDownLatch.getCount());
            }
//            while (true) {
//                long count = countDownLatch.getCount();
//                System.out.println(System.nanoTime()+"--"+ count);
//                Thread.sleep(1000);
//                if (count == 0) {
//                    break;
//                }
//            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } finally {
//            executorService.shutdown();
        }
    }
}
