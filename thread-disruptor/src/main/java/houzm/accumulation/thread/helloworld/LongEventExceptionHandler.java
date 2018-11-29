package houzm.accumulation.thread.helloworld;

import com.lmax.disruptor.ExceptionHandler;

/**
 * Author: hzm_dream@163.com
 * Date:  2018/11/20 13:39
 * Modified By:
 * Description： 异常处理
 */
public class LongEventExceptionHandler implements ExceptionHandler<LongEvent> {
    @Override
    public void handleEventException(Throwable throwable, long l, LongEvent longEvent) {
        throwable.printStackTrace();
    }

    @Override
    public void handleOnStartException(Throwable throwable) {
        throwable.printStackTrace();
    }

    @Override
    public void handleOnShutdownException(Throwable throwable) {
        throwable.printStackTrace();
    }
}
