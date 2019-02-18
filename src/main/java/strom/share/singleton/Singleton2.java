package strom.share.singleton;

import java.util.stream.Stream;

/**
 * 单元素的枚举类型已经成为实现Singleton的最佳方法
 * <p>
 * 使用枚举除了线程安全和防止反射强行调用构造器之外，
 * 还提供了自动序列化机制，防止反序列化的时候创建新的对象。
 * 因此，Effective Java推荐尽可能地使用枚举来实现单例。
 */
public enum Singleton2 {

    INSTANCE;


    Singleton2() {
        System.out.println("I'm singleton2.");
    }

    public static void main(String[] args) {
        Thread[] threads = new Thread[200];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() ->
            {
                Singleton2 instance = Singleton2.INSTANCE;
            }
            );
        }
        Stream.of(threads).forEach(t -> t.start());
    }
}
