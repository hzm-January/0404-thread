package houzm.game.thread.base.aaa;

/**
 * Package: houzm.game.thread.base.aaa
 * Author: hzm_dream@163.com
 * Date: Created in 2018/11/14 16:07
 * Copyright: Copyright (c) 2018
 * Version: 0.0.1
 * Modified By:
 * Description： TODO
 */
public class User {
    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
