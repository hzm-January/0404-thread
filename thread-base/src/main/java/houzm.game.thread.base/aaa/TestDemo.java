package houzm.game.thread.base.aaa;

import java.util.stream.IntStream;

/**
 * Package: houzm.game.thread.base.aaa
 * Author: hzm_dream@163.com
 * Date: Created in 2018/11/14 15:47
 * Copyright: Copyright (c) 2018
 * Version: 0.0.1
 * Modified By:
 * Description： TODO
 */
public class TestDemo {
    public static void main(String[] args) {
        show();
    }
    public static void show() {
        String c = "11";
        final String a = c;
        System.out.println(a); //11
        c = "22";
        System.out.println(a); //11

        User user = new User("aaa", 11);
        final User userfinal = user;
        System.out.println(userfinal);
        user = new User("bbb", 22);
        System.out.println(userfinal);


//        IntStream.rangeClosed(0, 9).forEach(key->{
//            new Runnable() {
//                @Override
//                public void run() {
//                    String b = new String();
//                    a = b;
//                }
//            }.run();
//        });

    }
}
