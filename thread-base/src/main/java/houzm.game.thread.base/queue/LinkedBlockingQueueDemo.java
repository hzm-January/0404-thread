package houzm.game.thread.base.queue;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * Package: houzm.game.thread.base.queue
 * Author: houzm
 * Date: Created in 2018/7/18 13:43
 * Copyright: Copyright (c) 2018
 * Version: 0.0.1
 * Modified By:
 * Description： TODO
 */
public class LinkedBlockingQueueDemo {
    public static void main(String[] args) {
        LinkedBlockingQueue<Integer> linkedBlockingQueue = new LinkedBlockingQueue<>(10);
        IntStream.rangeClosed(1, 10).forEach(key-> {
            try {
                linkedBlockingQueue.offer(key, 1, TimeUnit.SECONDS);
                System.out.println("--");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        while (true) {
            try {
                Integer poll = linkedBlockingQueue.poll(1, TimeUnit.SECONDS);
                System.out.println(linkedBlockingQueue.size());
                if (poll == null) {
                    return;
                }
                System.out.println(Thread.currentThread().getName()+" 取："+poll);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
