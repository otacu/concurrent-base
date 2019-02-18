package strom.container.demo1;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class D6_SynchronousQueue {

    public static void main(String[] args) throws Exception {
        // 一种特殊的 TransferQueue，容量为0
        BlockingQueue<Integer> queue = new SynchronousQueue<>();
        // 消费者线程
        new Thread(() -> {
            try {
                System.out.println(queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        // 没有消费者，无限制阻塞，内部调用transfer
        queue.put(1);
//        queue.put(2);
        // 没有消费者，抛异常
      //  queue.add(1);

        System.out.println(queue);
    }
}
