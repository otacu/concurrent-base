package strom.container.starter;


import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

/*
  需求： 用N张票，每张票有一个编号，同时有10个线程卖票
 */
public class TicketSeller2 {
    /**
     * Vector是同步的
     */
    static Vector<String> tickets = new Vector<>();

    static {
        for (int i = 0; i < 10000; i++) {
            tickets.add("高铁票 D" + i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (tickets.size() > 0) {

                   /* try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }*/

                    System.out.println("卖了 " + tickets.remove(0));
                }
            }).start();
        }
    }
}
