package strom.share.demo003;

import java.util.concurrent.TimeUnit;

/*
  对象引用发送改变会释放锁
 */
public class T4 {
    static Object o = new Object();

    public static void main(String[] args) throws Exception {

        new Thread(() -> {
            synchronized (o) {
                System.out.println(Thread.currentThread().getName() + " start");
                try {
                    Thread.sleep(20000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println(Thread.currentThread().getName() + " end");
                }
            }

        }, "T1").start();

        TimeUnit.SECONDS.sleep(3);
        o = new Object();

        new Thread(() -> {
            synchronized (o) {
                System.out.println(Thread.currentThread().getName() + " start");
            }
        }, "T2").start();
    }

}
