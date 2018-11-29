package houzm.accumulation.thread;

import java.util.concurrent.CountDownLatch;
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
public class PoolSubmitNest {

    public static void main(String[] args) {

        ExecutorService poolout = Executors.newCachedThreadPool();

        CountDownLatch countDownLatch = new CountDownLatch(10*10);

        long startTime = System.currentTimeMillis();
        IntStream.rangeClosed(1, 10).forEach(key -> {
            poolout.submit(new Runnable() {
                public void run() {
                    long startTimeIn = System.currentTimeMillis();
                    System.out.println(" i am out " + key + " : " + Thread.currentThread().getName());
                    countDownLatch.countDown();
                    // 这里如果是用intstream抛出异常：
                    // java.lang.VerifyError: Bad type on operand stack
                    // Type '[Ljava/lang/String;' (current frame, stack[1]) is not assignable to
                    CountDownLatch countDownLatchIn = new CountDownLatch(10);
                    for (int i = 0; i < 10; i++) {
                        poolout.submit(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    TimeUnit.SECONDS.sleep(20L);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                countDownLatch.countDown();
                                countDownLatchIn.countDown();
                                System.out.println(" i am in : " + Thread.currentThread().getName());
                            }
                        });
                    }
                    try {
                        countDownLatchIn.await(1000, TimeUnit.SECONDS);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("++++++++++++++++内部耗时："+ (System.currentTimeMillis()-startTime));
                }
            });
        });

        try {
            countDownLatch.await(1000, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("--------------总耗时："+ (System.currentTimeMillis()-startTime));
    }
}
