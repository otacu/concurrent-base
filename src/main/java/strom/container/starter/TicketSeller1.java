package strom.container.starter;


import java.util.ArrayList;
import java.util.List;

/*
  需求： 用N张票，每张票有一个编号，同时有10个线程卖票
 */
public class TicketSeller1 {

    static List<String> tickets = new ArrayList<>();

    static {
        for (int i = 0; i < 10000; i++) {
            tickets.add("高铁票 D" + i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (tickets.size() > 0) {
                    System.out.println("卖了 " + tickets.remove(0));
                }
            }).start();
        }
    }
}
