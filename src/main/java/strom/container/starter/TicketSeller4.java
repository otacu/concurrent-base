package strom.container.starter;


import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;

/*
  需求： 用N张票，每张票有一个编号，同时有10个线程卖票
 */
public class TicketSeller4 {

    static Queue<String> tickets = new ConcurrentLinkedQueue<>();

    static {
        for (int i = 0; i < 10000; i++) {
            tickets.add("高铁票 D" + i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (true) {
                    // 原子操作
                    String t = tickets.poll();
                    if (t == null) break;
                    else System.out.println(t);
                }

            }).start();
        }
    }
}
