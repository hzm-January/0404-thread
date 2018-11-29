package houzm.game.thread.base.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Package: houzm.game.thread.base.countdownlatch
 * Author: houzm
 * Date: Created in 2018/6/22 14:41
 * Copyright: Copyright (c) 2018
 * Version: 0.0.1
 * Modified By:
 * Description： CountDownLatch
 */
public class CountDownLatchDemo {

    public static void main(String[] args) {
        final CountDownLatch countDownLatch = new CountDownLatch(2);

        new Thread(new Runnable() {
            public void run() {
                Thread currentThread = Thread.currentThread();
                System.out.println(currentThread.getName()+"running...");
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
                System.out.println(currentThread.getName()+"run over...");

            }
        }, "thread-1").start();
        new Thread(new Runnable() {
            public void run() {
                Thread currentThread = Thread.currentThread();
                System.out.println(currentThread.getName()+"running....");
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
                System.out.println(currentThread.getName()+"run over...");
            }
        }, "thread-2").start();
        try {
            System.out.println("主线程进入等待....");
            countDownLatch.await(1000, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(countDownLatch.getCount()+"其他线程已执行完成，继续执行主线程....");
    }
}
