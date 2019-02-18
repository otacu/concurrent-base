package strom.share.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用reentrantLock 可以完成synchronized同样的功能
 * 需要注意的时候需要手动释放锁！！！
 * synchronized锁定的话，遇到异常jvm会自动释放，但是lock必须手动释放，常在finally做释放操作
 * <p>
 * ReentrantLock 可以指定为公平锁
 */
public class ReentrantLock2 extends Thread {

    // 指定为公平锁，公平但是效率比非公平锁低
    Lock lock = new ReentrantLock(false);

    public void run() {

        for (int i = 0; i < 100; i++) {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "获得锁！");
            } finally {
                lock.unlock();
            }
        }

    }


    public static void main(String[] args) throws Exception {
        ReentrantLock2 rl = new ReentrantLock2();
        Thread t1 = new Thread(rl);
        Thread t2 = new Thread(rl);
        t1.start();
        t2.start();

    }


}
