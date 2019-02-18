package strom.share.singleton;

import java.io.Serializable;
import java.util.stream.Stream;

/**
 * 线程安全的单例模式
 *
 * 静态内部类写法：这样就避免了静态实例在Singleton类加载的时候就创建对象，
 * 并且由于静态内部类只会被加载一次，所以这种写法也是线程安全的
 */
public class Singleton1 {

    private Singleton1() {
        System.out.println("I'm singleton1.");
    }

    public static Singleton1 getSingleton() {
        return Holder.singleton1;
    }

    // 静态内部类写法
    private static class Holder {
        private static Singleton1 singleton1 = new Singleton1();
    }

    public static void main(String[] args) {
        Thread[] threads = new Thread[200];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(Singleton1::getSingleton);
        }
        Stream.of(threads).forEach(t -> t.start());

    }
}
