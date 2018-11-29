package houzm.game.thread.base.thread.threadpool;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * Package: houzm.game.thread.base.thread.threadpool
 * Author: houzm
 * Date: Created in 2018/6/22 20:47
 * Copyright: Copyright (c) 2018
 * Version: 0.0.1
 * Modified By:
 * Description： TODO
 */
public class newCachedThreadPoolExcutorDemo {

    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newCachedThreadPool();
//        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor()
        threadPool.submit(new Callable<Boolean>() {
            public Boolean call() throws Exception {
                int[] ints = IntStream.range(0, 20).toArray();
                Boolean flag = false;
                for (int anInt : ints) {
                    System.out.println(Thread.currentThread().getName()+"==="+ anInt);
                    if (anInt == 12) {
                        flag = true;
                    } else {
                        flag = false;
                    }
                    if (flag) {
                        System.out.println("===shutdown===");
                        threadPool.shutdownNow();
                    }
                }

                return flag;
            }
        });
        threadPool.submit(new Callable<Boolean>() {
            public Boolean call() throws Exception {
                int[] ints = IntStream.range(0, 10).toArray();
                Boolean flag = false;
                for (int anInt : ints) {
                    System.out.println(Thread.currentThread().getName()+">>>"+ anInt);
                    if (anInt == 9) {
                        flag = true;
                    } else {
                        flag = false;
                    }
                    if (flag) {
                        System.out.println("===shutdown===");
                        threadPool.shutdownNow();
                    }
                }
                return flag;
            }
        });

    }

}
