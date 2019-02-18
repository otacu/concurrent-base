package strom.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class D7_CacheThreadPool {

    public static void main(String[] args) throws Exception {
        // 刚开始线程池里面一个线程都没有，来了一个任务就启动一个，再来一个又启动一个，
      //  如果有空闲的线程，就拿空闲的，没有就新启动一个，知道启动到cpu爆掉
        // alive time 60s,超过就会销毁
        ExecutorService pool = Executors.newCachedThreadPool();
        System.out.println(pool);

        for (int i = 0; i < 2; i++) {
            pool.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            });
        }

        System.out.println(pool);

        TimeUnit.SECONDS.sleep(70);
        System.out.println(pool);

        // shutdown
    }
}
