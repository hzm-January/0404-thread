package houzm.accumulation.thread.helloworld;

import java.util.concurrent.ThreadFactory;

/**
 * Author: hzm_dream@163.com
 * Date:  2018/11/20 14:13
 * Modified By:
 * Description：
 */
public class LongEeventThreadFactory implements ThreadFactory {
    @Override
    public Thread newThread(Runnable r) {
        return new Thread(r, "Simple Disruptor Test Thread");
    }
}
