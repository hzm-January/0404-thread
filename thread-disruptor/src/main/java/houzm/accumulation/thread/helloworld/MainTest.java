package houzm.accumulation.thread.helloworld;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

/**
 * Author: hzm_dream@163.com
 * Date:  2018/11/20 14:09
 * Modified By:
 * Description：
 */
public class MainTest {
    public static void main(String[] args) {
        long value = 111;
        int ringBufferSize = 1024; //必须是2的n次方
        Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(new LongEventFactory(),
                ringBufferSize, new LongEeventThreadFactory(),
                ProducerType.SINGLE, new BlockingWaitStrategy());
        disruptor.handleEventsWith(new LongEventHandler());
        disruptor.setDefaultExceptionHandler(new LongEventExceptionHandler());
        RingBuffer<LongEvent> ringBuffer = disruptor.start();
        LongEventProducer producer = new LongEventProducer(ringBuffer);
        producer.onData(value);
    }
}
