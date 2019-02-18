package strom.threadpool;

import java.util.concurrent.*;

public class D5_Future {

    public static void main(String[] args) throws Exception {
        FutureTask<Integer> futureTask = new FutureTask<>(() -> {
            TimeUnit.SECONDS.sleep(1);
            return 100;
        });
        new Thread(futureTask).start();
        // 阻塞，等待执行完成
        System.out.println(futureTask.get());
        // ==============================

        ExecutorService pool = Executors.newFixedThreadPool(1);
        Future<Integer> future = pool.submit(() -> {
            TimeUnit.SECONDS.sleep(1);
            return 200;
        });
        System.out.println(future.isDone());
        System.out.println(future.get());
        System.out.println(future.isDone());
        // shutdown

    }
}
