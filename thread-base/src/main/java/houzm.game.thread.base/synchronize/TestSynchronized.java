package houzm.game.thread.base.synchronize;

import java.util.stream.IntStream;

/**
 * Package: houzm.game.thread.base.synchronize
 * Author: houzm
 * Date: Created in 2018/7/9 12:05
 * Copyright: Copyright (c) 2018
 * Version: 0.0.1
 * Modified By:
 * Description： 测试Synchronized
 */
public class TestSynchronized {
    public static void main(String[] args) {
        CountOfTestSynchronized countObj = new CountOfTestSynchronized();
        for (int i = 0; i < 5; i++) {
            ThreadOfSynchronized thread = new ThreadOfSynchronized(countObj);
            thread.start();
        }
//        IntStream.rangeClosed(0, 4).forEach(key -> {
//                    ThreadOfSynchronized thread = new ThreadOfSynchronized(countObj);
//                    thread.start();
//                }
//        );
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("计算结果："+countObj.getCount());
    }
}
