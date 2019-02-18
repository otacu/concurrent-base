package strom.share.demo004;

import java.util.concurrent.TimeUnit;

/**
 * volatile 变量在多个线程中可见性
 * A B线程都使用同一个变量，java默认是A线程copy了一份，B修改修改了此变量，
 * A线程怎么知道？
 *
 *  当线程t1开始运行的时候，会把running值从内存中读到t1线程的工作区，在运行过程中直接使用copy
 *  并不会每次都去读堆内存，这样，当主线程修改running的值之后，t1线程感知不到，所以不会停止。
 *
 *  使用volatile会强制所有线程去主内存中读取running变量的值
 *
 *  volatile并不能保证多个线程同时修改running值带来的不一致性问题。所以所volatile不能替代synchronized
 *
 */
public class T {
    // 对比 有无 volatile 关键字
    /*volatile*/  boolean run = true;

    void m() {
        System.out.println("m start....");
        while (run) {
            // 空闲时间有可能去会去主内存刷新，不稳定
            //  TimeUnit.SECONDS.sleep(1);
        }
        System.out.println("m end.");
    }

    public static void main(String[] args) throws Exception {
        T t = new T ();
        new Thread(t::m).start();

        TimeUnit.SECONDS.sleep(1);

        t.run = false;
    }


}
