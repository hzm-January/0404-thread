package houzm.accumulation.thread.helloworld;

import com.lmax.disruptor.EventTranslatorOneArg;

/**
 * Author: hzm_dream@163.com
 * Date:  2018/11/20 13:52
 * Modified By:
 * Description：
 */
public class LongEventTranslator implements EventTranslatorOneArg<LongEvent, Long>{

    @Override
    public void translateTo(LongEvent longEvent, long l, Long aLong) {
        longEvent.setValue(aLong);
    }
}
