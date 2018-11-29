package houzm.game.thread.base.synchronize;

/**
 * Package: houzm.game.thread.base.synchronize
 * Author: houzm
 * Date: Created in 2018/7/9 12:44
 * Copyright: Copyright (c) 2018
 * Version: 0.0.1
 * Modified By:
 * Description： Thread
 */
public class ThreadOfSynchronized extends Thread{

    public CountOfTestSynchronized count;

    public ThreadOfSynchronized(CountOfTestSynchronized count) {
        this.count = count;
    }

    @Override
    public void run() {
        count.increase();
    }

}
