package houzm.game.thread.base.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

/**
 * Package: houzm.game.thread.base.callable
 * Author: houzm
 * Date: Created in 2018/6/29 12:53
 * Copyright: Copyright (c) 2018
 * Version: 0.0.1
 * Modified By:
 * Description： TODO
 */
public class CallableException {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<Boolean> future1 = executorService.submit(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                Thread.sleep(5000);
                IntStream.range(1, 100).forEach(key -> {
                    if (key == 111) {
                        throw new MyException(Thread.currentThread().getName() + "------10");
                    }
                });
                return null;
            }
        });
        Future<Object> future2 = executorService.submit(() -> {
            Thread.sleep(7000);
            IntStream.range(1, 100).forEach(key -> {
                if (key == 11) {
                    throw new MyException(Thread.currentThread().getName() + "------19");
                }
            });
            return Boolean.valueOf(false);
        });

        try {
            System.out.println("=======before get========");
            System.out.println(future1.get());
            System.out.println(future2.get());
            System.out.println("=======after get========");
        } catch (MyException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }

        /**
         * java.util.concurrent.ExecutionException: houzm.game.thread.base.callable.MyException: pool-1-thread-2------19
         at java.util.concurrent.FutureTask.report(FutureTask.java:122)
         at java.util.concurrent.FutureTask.get(FutureTask.java:192)

         线程执行抛出异常，被包装成ExecutionException，不满足res中spring统一异常处理的需求
         */
    }
}
