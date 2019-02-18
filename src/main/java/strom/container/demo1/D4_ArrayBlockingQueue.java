package strom.container.demo1;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class D4_ArrayBlockingQueue {
    // 有界队列
    static  BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 10; i++) {
            queue.put(i);
        }
        // 满了就会异常 java.lang.IllegalStateException: Queue full
      //   queue.add(11);
        // 满了阻塞
         queue.put(11);
        //不会异常，通过返回值判断是否成功
       // queue.offer(11);
        // 时间段阻塞
       // queue.offer(11, 1, TimeUnit.SECONDS);

        System.out.println(queue);

    }

}
