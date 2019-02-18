package strom.share.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用reentrantLock 可以完成synchronized同样的功能
 * 需要注意的时候需要手动释放锁！！！
 * synchronized锁定的话，遇到异常jvm会自动释放，但是lock必须手动释放，常在finally做释放操作
 */
public class ReentrantLock1 {

    Lock lock = new ReentrantLock();

    void m1() {
        lock.lock();  // 相当于synchronized(this)
        try {
            for (int i = 0; i < 10; i++) {
                System.out.println(i);
                TimeUnit.SECONDS.sleep(1);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    void m2() {
        lock.lock();  // 相当于synchronized(this)
        try {
            System.out.println("m2");
        } finally {
            lock.unlock();
        }
    }

    /**
     * 使用tryLock尝试锁定，不管锁定与否，程序可以继续执行，
     *  可以根据tryLock的返回值判断是否锁定
     *  也可以指定tryLock时间
     */
    void m3() {
        boolean locked =false;

       /* locked = lock.tryLock();
        if(locked) lock.unlock();*/

        try {
            locked = lock.tryLock(5, TimeUnit.SECONDS);
            System.out.println("m3  lock");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if(locked) lock.unlock();
        }
    }

    public static void main(String[] args) throws Exception {
        ReentrantLock1 rl = new ReentrantLock1();
        new Thread(rl::m1).start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(rl::m2).start();

    }


}
