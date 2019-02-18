package strom.share.demo001;

/**
 * synchronized
 * 关键字对某个对象加锁，synchronized的代码块是原子操作，不可分
 */
public class T {

    public int count = 100;
    private Object o = new Object();

    public void m() {
        // 任何线程想要执行下面的代码，先要拿到o的锁
        synchronized (o) {
            count--;
            System.out.println(Thread.currentThread().getName() + " count:" + count);
        }
    }

    public void m1() {
        // 任何线程想要执行下面的代码，先要拿到this的锁
        synchronized (this) {
            count--;
            System.out.println(Thread.currentThread().getName() + " count:" + count);
        }
    }

    // 等同synchronized (this)
    public synchronized void m2() {
            count--;
            System.out.println(Thread.currentThread().getName() + " count:" + count);

    }


    public static void main(String[] args) {
        T t = new T();
        t.m();
    }
}
