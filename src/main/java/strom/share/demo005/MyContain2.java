package strom.share.demo005;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/***
 *
 实现一个容器，提供两个方法 add size，写两个线程线程1添加10个元素进去，线程2监听容器的个数
 当个数到5个，线程2给出提示并结束。

 使用 notify/wait 机制，wait会释放锁，notify不会释放锁，必须加锁才可以使用，调用被锁定对象的wait notify
 */
public class MyContain2 {

    volatile List<Object> list = new ArrayList<>();

    public void add(Object o) {
        list.add(o);
    }

    public int size() {
        return list.size();
    }

    public static void main(String[] args) {
        MyContain2 c = new MyContain2();
        final Object o = new Object();

        new Thread(() -> {
            synchronized (o) {
                if (c.size() != 5) {
                    try {
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                o.notify();
            }
            System.out.println("t2 结束。");
        }, "t2").start();


        new Thread(() -> {
            System.out.println("t1 启动");
            synchronized (o) {
                for (int i = 0; i < 10; i++) {
                    try {
                        c.add(new Object());
                        System.out.println("add:" + i);
                        if (c.size() == 5) {
                            o.notify();
                            o.wait();
                        }
                        TimeUnit.SECONDS.sleep(1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "t1").start();
    }

}
