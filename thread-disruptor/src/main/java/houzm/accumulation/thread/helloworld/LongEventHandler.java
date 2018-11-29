package houzm.accumulation.thread.helloworld;


import com.lmax.disruptor.EventHandler;

/**
 * Author: hzm_dream@163.com
 * Date:  2018/11/20 13:27
 * Modified By:
 * Description：消费者
 */
public class LongEventHandler implements EventHandler<LongEvent> {

    @Override
    public void onEvent(LongEvent longEvent, long l, boolean b) throws Exception {
        System.out.println(longEvent.getValue());
    }
}
