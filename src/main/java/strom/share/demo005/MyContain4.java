package strom.share.demo005;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/***
 *
 实现一个容器，提供两个方法 add size，写两个线程线程1添加10个元素进去，线程2监听容器的个数
 当个数到5个，线程2给出提示并结束。

 Semaphore是一种基于计数的信号量。它可以设定一个阈值，基于此，
 多个线程竞争获取许可信号，做完自己的申请后归还，超过阈值后，线程申请许可信号将会被阻塞。
 Semaphore可以用来构建一些对象池，资源池之类的，比如数据库连接池，我们也可以创建计数为1的Semaphore，
 将其作为一种类似互斥锁的机制，这也叫二元信号量，表示两种互斥状态

 CyclicBarrier/
 Phaser JDK1.7
 */
public class MyContain4 {

    volatile List<Object> list = new ArrayList<>();

    public  void add (Object o) {
        list.add(o);
    }

    public int size () {
        return list.size();
    }

    public static void main(String[] args) {
        MyContain4 c = new MyContain4();
        // 信号量
        Semaphore semaphore = new Semaphore(0);

        new Thread(() -> {
            System.out.println("t1启动");
            for (int i = 0; i<10; i++) {
                c.add(new Object());
                System.out.println("add:" + i);

                if (c.size() == 5) {
                    semaphore.release();
//                    System.out.println(semaphore.getQueueLength());

                }
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        },"t1").start();

        new Thread(() -> {

            if (c.size() != 5) {
                try {
                    System.out.println(semaphore.getQueueLength());
                    // 等待申请许可
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("t2 结束。");
        },"t2").start();
    }

}
