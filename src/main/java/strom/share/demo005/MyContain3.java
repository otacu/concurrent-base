package strom.share.demo005;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/***
 *
 实现一个容器，提供两个方法 add size，写两个线程线程1添加10个元素进去，线程2监听容器的个数
 当个数到5个，线程2给出提示并结束。

 CountDownLatch内部会维护一个初始值为线程数量的计数器，主线程执行await方法，
 如果计数器大于0，则阻塞等待。当一个线程完成任务后，计数器值减1。
 当计数器为0时，表示所有的线程已经完成任务，等待的主线程被唤醒继续执行。
 */
public class MyContain3 {

    volatile List<Object> list = new ArrayList<>();

    public  void add (Object o) {
        list.add(o);
    }

    public int size () {
        return list.size();
    }

    public static void main(String[] args) {
        MyContain3 c = new MyContain3();
        // 门闩
        CountDownLatch cdl = new CountDownLatch(1);

        new Thread(() -> {
            System.out.println("t1启动");
            for (int i = 0; i<10; i++) {
                c.add(new Object());
                System.out.println("add:" + i);

                if (c.size() == 5) {
                    cdl.countDown();
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
                    // 等待
                    cdl.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("t2 结束。");
        },"t2").start();
    }

}
