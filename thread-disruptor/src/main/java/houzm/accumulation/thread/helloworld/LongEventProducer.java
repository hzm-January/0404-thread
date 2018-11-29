package houzm.accumulation.thread.helloworld;

import com.lmax.disruptor.RingBuffer;

/**
 * Author: hzm_dream@163.com
 * Date:  2018/11/20 13:42
 * Modified By:
 * Description：生产者
 */
public class LongEventProducer {

    private RingBuffer<LongEvent> ringBuffer;

    public LongEventProducer(RingBuffer<LongEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void onData(Long value) {
        // 3.0 以下版本
        /*long sequence = ringBuffer.next();
        try {
            LongEvent longEvent = ringBuffer.get(sequence);
            longEvent.setValue(value);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //发布时间
            ringBuffer.publish(sequence);
        }*/

        // 3.0 以上版本
        ringBuffer.publishEvent(new LongEventTranslator(), value);
    }
}
