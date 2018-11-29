package houzm.game.thread.base.callable;

/**
 * Package: houzm.game.thread.base.callable
 * Author: houzm
 * Date: Created in 2018/6/29 13:26
 * Copyright: Copyright (c) 2018
 * Version: 0.0.1
 * Modified By:
 * Description： TODO
 */
public class MyException extends RuntimeException {

    private String message;

    public MyException(String message) {
        super(message);
    }

}
