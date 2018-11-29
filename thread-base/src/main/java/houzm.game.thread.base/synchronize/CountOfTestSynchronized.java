package houzm.game.thread.base.synchronize;

/**
 * Package: houzm.game.thread.base.synchronize
 * Author: houzm
 * Date: Created in 2018/7/9 12:10
 * Copyright: Copyright (c) 2018
 * Version: 0.0.1
 * Modified By:
 * Description： count
 */
public class CountOfTestSynchronized {

    private byte[] bytes = new byte[1];
    private int count = 0;

    public synchronized void increaseOfSynchronized(){
        try {
            Thread.sleep(50); //模仿干活
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        count++;
        System.out.println(Thread.currentThread().getName()+ " : " + count);
    }
    public void increase2OfSynchronized(){
        synchronized (bytes) {
            try {
                Thread.sleep(50); //模仿干活
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count++;
            System.out.println(Thread.currentThread().getName()+ " : " + count);
        }
    }
    public void increase(){
        try {
            Thread.sleep(10000); //模仿干活
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        System.out.println(Thread.currentThread().getName()+ " before : " + count);
        /**
         * 1.取count值
         * 2.count+1
         * 3.赋值给count
         */
        count+=1; //count=count+1;
        System.out.println(Thread.currentThread().getName()+ " end : " + count);
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
