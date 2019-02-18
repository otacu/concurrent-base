package strom.container.demo1;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class D3_ConcurrentLinkedQueue {

    public static void main(String[] args) {
        // 无界队列，单向
        Queue<String> queue = new ConcurrentLinkedQueue<>();

        for (int i = 0; i < 10; i++) {
          //  queue.add("queue" + i);
            queue.offer("queue" + i);
        }

        System.out.println(queue);
        System.out.println(queue.size());
        // 拿走一个，删掉
        System.out.println(queue.poll());
        System.out.println(queue.size());
        // 拿走一个，不删掉
        System.out.println(queue.peek());
        System.out.println(queue.size());

        // 双端队列Deque
    }
}
