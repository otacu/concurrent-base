package strom.share.demo002;

/**
 * synchronized
 * 关键字对某个对象加锁
 */
public class T {

    public static  int count = 100;

    public static  void m1() {
        // 是否可以synchronized (this)?
        synchronized (T.class) {
            count--;
            System.out.println(Thread.currentThread().getName() + " count:" + count);
        }
    }

    // 等同于synchronized (T.class)，相当于锁住trom.share.demo002.T.class对象
    public synchronized static void m2() {
            count--;
            System.out.println(Thread.currentThread().getName() + " count:" + count);

    }


}
