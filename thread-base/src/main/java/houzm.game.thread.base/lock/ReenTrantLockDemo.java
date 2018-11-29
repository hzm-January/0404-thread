package houzm.game.thread.base.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Package: houzm.game.thread.base.lock
 * Author: houzm
 * Date: Created in 2018/7/10 13:05
 * Copyright: Copyright (c) 2018
 * Version: 0.0.1
 * Modified By:
 * Description： TODO
 */
public class ReenTrantLockDemo {
    private ReentrantLock lock = new ReentrantLock();
    public void get() {
        lock.lock();
        System.out.println(Thread.currentThread().getName()+" get : begin");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+" get : end");
        lock.unlock();
    }
    public void put() {
        lock.lock();
        System.out.println(Thread.currentThread().getName()+" put : begin");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+" put : end");
        lock.unlock();
    }
}
