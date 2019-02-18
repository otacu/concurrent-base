package strom.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class D9_WorkStealingThreadPool {

    public static void main(String[] args) {
        // 创建一个拥有多个任务队列（以便减少连接数）,每个线程都维护自己的队列；如果任务执行完了
        // 会去其他队列工作窃取任务
        ExecutorService executorService = Executors.newWorkStealingPool();
        System.out.println(Runtime.getRuntime().availableProcessors());
    }
}
