package strom.share.demo003;

/**
 * 同步方法和非同步方法是否可以同时调用？
 */
public class T3 {

    public synchronized void m1() {
        try {
            System.out.println(Thread.currentThread().getName() + " m1() start....");
            Thread.sleep(5000);
            System.out.println(Thread.currentThread().getName() + " m1() end");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void m2()  {
        try {
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName() + " m2() end");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws Exception {
        T3 t = new T3();
        new Thread(() -> t.m1(), "T1").start();
        new Thread(() -> t.m2(), "T2").start();

        /*
        new Thread(t::m1, "T1").start();
        new Thread(t::m2, "T2").start();
        */
    }

}
