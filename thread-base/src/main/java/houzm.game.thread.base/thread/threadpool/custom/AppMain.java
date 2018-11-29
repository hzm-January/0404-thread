package houzm.game.thread.base.thread.threadpool.custom;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * Package: houzm.game.thread.base.thread.threadpool.custom
 * Author: houzm
 * Date: Created in 2018/7/24 13:05
 * Copyright: Copyright (c) 2018
 * Version: 0.0.1
 * Modified By:
 * Description： customThreadPool测试
 */
public class AppMain {

    public static void main(String[] args) {
        CustomThreadPool customThreadPool = CustomThreadPool.getCustomThreadPool();
//        CustomThreadPool customThreadPool2 = CustomThreadPool.getCustomThreadPool();
////        CustomThreadPool customThreadPool10 = CustomThreadPool.getCustomThreadPool();
////        System.out.println(customThreadPool == customThreadPool2);
////        System.out.println(customThreadPool == customThreadPool10);
        //测试是否单例
//        IntStream.rangeClosed(1,100).parallel().forEach(key->{
//            CustomThreadPool customThreadPool3 = CustomThreadPool.getCustomThreadPool();
//            System.out.println(Thread.currentThread().getName()+"======"+ (customThreadPool3 == customThreadPool));
//        });
        AtomicInteger atomicInteger = new AtomicInteger();
        IntStream.rangeClosed(1,1000).forEach(key->{
            customThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    String name = Thread.currentThread().getName();
                    System.out.println(atomicInteger.getAndIncrement()+"---"+name);
                }
            });
        });

//        customThreadPool.shutdown();

    }
}
