package strom.share.demo003;

/*
  死锁
 */
public class DeadT {


    public static void main(String[] args) throws Exception {
        Object bigGate = new Object();
        Object smallGate = new Object();
        new Thread(() -> {

            String name = Thread.currentThread().getName();
            synchronized (bigGate) {
                System.out.println(name + ":我把大门给锁了...然后我休息一下...");
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(name + ":我现在要进入小门.....");
                synchronized (smallGate) {
                    System.out.println(name + ":我永远都进不来啊.....");
                }

            }

        }, "小明").start();
        new Thread(() -> {
            String name = Thread.currentThread().getName();
            synchronized (smallGate) {
                System.out.println(name + ":我把小门给锁了...然后我休息一下...");
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(name + ":我现在要进入大门.....");
                synchronized (bigGate) {
                    System.out.println(name + ":我永远都进不来啊.....");
                }

            }

        }, "小红").start();

    }

}
