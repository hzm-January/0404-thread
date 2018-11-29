package houzm.accumulation.thread.helloworld;

import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.event.EventType;

/**
 * Author: hzm_dream@163.com
 * Date:  2018/11/20 13:22
 * Modified By:
 * Description：数据对象
 *
 *      声明一个Event来包含需要传递的数据
 */
public class LongEvent {
    private long value;

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }
}
