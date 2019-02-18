package strom.container.demo1;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class D4_LinkedBlockingQueue {

    static  BlockingQueue<String> queue = new LinkedBlockingQueue<>();

    static Random random = new Random();

    public static void main(String[] args) {
        // 生产消费者模型
        // 生产线程
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                try {
                    // 如果满了，就会等待
                    queue.put("queue" + i);
                    TimeUnit.MILLISECONDS.sleep(random.nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "p1").start();
        // 消费线程
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                for (;;) {
                    try {
                        // 如果空了，就会等待
                        System.out.println(Thread.currentThread().getName() + " take:" + queue.take());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "c" +i ).start();
        }


    }
}
