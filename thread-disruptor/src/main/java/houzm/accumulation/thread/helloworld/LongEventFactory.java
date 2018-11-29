package houzm.accumulation.thread.helloworld;

import com.lmax.disruptor.EventFactory;

/**
 * Author: hzm_dream@163.com
 * Date:  2018/11/20 13:25
 * Modified By:
 * Description：event 工厂
 *
 *      需要让Disruptor创建事件
 *      声明了一个EventFactory来实例化Event对象
 *
 */
public class LongEventFactory implements EventFactory<LongEvent> {
    @Override
    public LongEvent newInstance() {
        return new LongEvent();
    }
}
