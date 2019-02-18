package strom.share.lock;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * 固定容量同步容器,拥有put和get方法，以及getCount方法
 * 能够支持2个生产者线程以及10个消费者线程的阻塞调用
 *
 * 1.使用wait和notify/notifyAll 实现
 *
 * 2.使用Lock和Condition来实现,可以精确通知那些线程被叫醒
 * @param <T>
 */
public class MyContainer<T> {

    private LinkedList<T> linkedList = new LinkedList<>();
    private int MAX = 10;
    private int count = 0;

    private Lock lock = new ReentrantLock();

    private Condition producer = lock.newCondition();
    private Condition consumer = lock.newCondition();

    public void put(T t) {
        try {
            lock.lock();
            // 想想为什么不用if？
            while (linkedList.size() == MAX) {
                producer.await();
            }

            linkedList.add(t);
            count++;
            consumer.signalAll(); // 通知消费者线程进行消费
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public T get() {
        try {
            lock.lock();
            while (linkedList.size() == 0) {
                consumer.await();
            }
            T t = linkedList.removeFirst();
            count--;
            producer.signalAll(); // 通知生产者线程进行生产
            return t;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
        MyContainer<String> c = new MyContainer<>();
        // 启动10个消费者线程
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 5; j++)
                    System.out.println("消费了：" + c.get());
            }, "c"+i).start();
        }

        TimeUnit.SECONDS.sleep(2);

        // 启动2个生产者线程
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                for (int j = 0; j < 25; j++) {
                    c.put(Thread.currentThread().getName() + " " + j);
                    System.out.println("生产了：" + Thread.currentThread().getName() + " " + j);
                }
            }, "p"+i).start();
        }
    }
}
