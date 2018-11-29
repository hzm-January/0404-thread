package houzm.game.thread.base.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * Package: houzm.game.thread.base.cyclicbarrier
 * Author: houzm
 * Date: Created in 2018/6/22 15:32
 * Copyright: Copyright (c) 2018
 * Version: 0.0.1
 * Modified By:
 * Description： CyclicBarrier最简单应用
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        int N = 4;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(N);
        for (int i = 0; i < N; i++) {
            new Writer(i, cyclicBarrier).start();
        }
    }
    static class Writer extends Thread{
        private CyclicBarrier cyclicBarrier;
        public Writer() {
        }
        public Writer(int key, CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+"正在执行..."+TimeUnit.MILLISECONDS);
            try {
                Thread.sleep(5000L);
                System.out.println(Thread.currentThread().getName()+"执行完成...");
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println("所有线程都执行完成...");
        }
    }
}
