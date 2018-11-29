package houzm.accumulation.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * Author: hzm_dream@163.com
 * Date:  2018/11/19 18:12
 * Modified By:
 * Description：嵌套线程池
 */
public class PoolExcuteNest {

    public static void main(String[] args) {

        ExecutorService poolout = ResThreadPoolAbortPolicy.getInstance();


        IntStream.rangeClosed(1, 10).forEach(key -> {
            poolout.execute(new Runnable() {
                public void run() {

                    System.out.println(" i am out " + key + " : " + Thread.currentThread().getName());
                    // 这里如果是用intstream抛出异常：
                    // java.lang.VerifyError: Bad type on operand stack
                    // Type '[Ljava/lang/String;' (current frame, stack[1]) is not assignable to
                    for (int i = 0; i < 10; i++) {
                        poolout.execute(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    TimeUnit.SECONDS.sleep(20L);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                System.out.println(" i am in " + key + " : " + Thread.currentThread().getName());
                            }
                        });
                    }
                }
            });
        });
    }
}
