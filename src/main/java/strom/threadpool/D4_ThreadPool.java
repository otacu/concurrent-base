package strom.threadpool;

import java.sql.Time;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class D4_ThreadPool {

    public static void main(String[] args) throws Exception {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 6; i++) {
            fixedThreadPool.execute(new Thread(() ->{
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            }));
        }

        System.out.println(fixedThreadPool);
        fixedThreadPool.shutdown();

        System.out.println(fixedThreadPool.isShutdown());
        System.out.println(fixedThreadPool.isTerminated());
        System.out.println(fixedThreadPool);

        TimeUnit.SECONDS.sleep(5);
        System.out.println(fixedThreadPool.isShutdown());
        System.out.println(fixedThreadPool.isTerminated());
        System.out.println(fixedThreadPool);

        // shutdown
    }
}
