package strom.share.demo005;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/***
 *
 实现一个容器，提供两个方法 add size，写两个线程线程1添加10个元素进去，线程2监听容器的个数
 当个数到5个，线程2给出提示并结束。
 */
public class MyContain1 {

    // 使用volatile ，使t2得到通知
    /*volatile*/ List<Object> list = new ArrayList<>();

    public  void add (Object o) {
        list.add(o);
    }

    public int size () {
        return list.size();
    }

    public static void main(String[] args) {
        MyContain1 c = new MyContain1();

        new Thread(() -> {
            for (int i = 0; i<10; i++) {
                c.add(new Object());
                System.out.println("add:" + i);

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        },"t1").start();

        new Thread(() -> {
            while (true) {
                if (c.size() == 5) {
                    break;
                }
            }
            System.out.println("t2 结束。");
        },"t2").start();
    }

}
