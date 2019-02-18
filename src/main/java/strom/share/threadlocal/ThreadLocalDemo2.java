package strom.share.threadlocal;

import lombok.Data;
import lombok.ToString;

import java.util.concurrent.TimeUnit;

/**
 * ThreadLocal 线程局部变量
 *
 * ThreadLocal是用空间换时间，synchronized使用时间换空间
 * 比如hibernate的session连接就存在ThreadLocal中，避免使用synchronized,
 * 保证线程安全的同时，效率更高
 *
 */
public class ThreadLocalDemo2 {

    @Data
    @ToString
    static class Person {
        String name = "tom";
    }

    static ThreadLocal<Person> tl = new ThreadLocal<>();

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " " + tl.get());
        }, "t1").start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Person p = new Person();
            p.name = "jack";
            tl.set(p);
            System.out.println(Thread.currentThread().getName() + " " + tl.get());
        }, "t2").start();
    }
}
