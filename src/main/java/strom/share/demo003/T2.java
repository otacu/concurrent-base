package strom.share.demo003;

public class T2 {

    synchronized void m1() {
        System.out.println(Thread.currentThread().getName() + " m1() start....");
        // TODO
        m2();
    }

    synchronized void m2() {
        System.out.println("m2()");
    }


    // 子类调用父类
    class TT extends  T2 {
        @Override
        synchronized void m2() {
            super.m2();
        }
    }
}
