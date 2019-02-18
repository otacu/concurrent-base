package strom.threadpool;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class D9_ScheduleThreadPoll {

    public static void main(String[] args) throws Exception {
        // 定时器线程池，定时执行任务，池中线程可复用。 spring-task Quartz底层使用的
        final ScheduledExecutorService pool = Executors.newScheduledThreadPool(4);
        for (int i = 0; i < 4; i++) {
            pool.scheduleAtFixedRate(() -> {
                try {
                    TimeUnit.MILLISECONDS.sleep(new Random().nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            }, 0, 1, TimeUnit.SECONDS);
        }

        TimeUnit.SECONDS.sleep(10);
        pool.shutdown();
    }
}
