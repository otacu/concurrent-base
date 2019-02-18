package strom.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class D8_SingleThreadPoll {

    public static void main(String[] args) {
        // 单例。保证任务顺序执行
        ExecutorService pool = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 5; i++) {
            final int j = i ;
            pool.execute(() -> {
                System.out.println(j+":" + Thread.currentThread().getName());
            });
        }

        pool.shutdown();
    }
}
