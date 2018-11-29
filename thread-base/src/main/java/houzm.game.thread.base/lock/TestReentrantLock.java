package houzm.game.thread.base.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Package: houzm.game.thread.base.lock
 * Author: houzm
 * Date: Created in 2018/7/9 12:02
 * Copyright: Copyright (c) 2018
 * Version: 0.0.1
 * Modified By:
 * Description： 测试ReentrantLock
 */
public class TestReentrantLock {


    public static void main(String[] args) {
        ReenTrantLockDemo reenTrantLockDemo = new ReenTrantLockDemo();
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                reenTrantLockDemo.put();
            }).start();
        }
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                reenTrantLockDemo.get();
            }).start();
        }
    }
}
