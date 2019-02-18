package strom.container.demo1;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TransferQueue;

/**
 *  更高的并发、处理实时消息
 *  Netty 用的比较多
 */
public class D5_TransferQueue {

    public static void main(String[] args) throws Exception {

        TransferQueue<Integer> queue = new LinkedTransferQueue<>();

      /*  new Thread(() -> {
            try {
                System.out.println(queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "c1").start();*/

       // 直接将消息转移给消费者，前提必须要有消费者，或者会阻塞
//        queue.transfer(111);
        // 尝试转移，通过返回值判断
//        boolean a = queue.tryTransfer(111);
//        System.out.println(a);
        // 尝试等待多长时间转移，通过返回值判断
//        boolean b = queue.tryTransfer(111, 1, TimeUnit.SECONDS);
//        System.out.println(b);

        // add put offer就是放到队列里面
      /*  new Thread(() -> {
            try {
                System.out.println(queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "c2").start();*/

    }
}
