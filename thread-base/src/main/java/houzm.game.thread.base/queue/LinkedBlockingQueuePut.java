package houzm.game.thread.base.queue;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * Package: houzm.game.thread.base.queue
 * Author: houzm
 * Date: Created in 2018/9/30 14:40
 * Copyright: Copyright (c) 2018
 * Version: 0.0.1
 * Modified By:
 * Description： 测试put阻塞
 */
public class LinkedBlockingQueuePut {
    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors() + 1,
                10, 100, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(10),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
        LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<>(2);
        Semaphore semaphore = new Semaphore(1);
        executor.submit(new Runnable() {
            @Override
            public void run() {
                IntStream.rangeClosed(0, 100).forEach(key-> {
                    try {
                        queue.put(key);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            }
        });
        executor.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        Integer poll = queue.poll(100, TimeUnit.MILLISECONDS);
                        if (poll == null) {
                            break;
                        }
                        System.out.println(poll);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
