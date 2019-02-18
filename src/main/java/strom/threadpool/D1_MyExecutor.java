package strom.threadpool;

import java.util.concurrent.Executor;

public class D1_MyExecutor implements Executor{
    @Override
    public void execute(Runnable command) {
        // new Thread(command).run();
        command.run();
    }

    public static void main(String[] args) {
        // 执行器 执行任务
        new D1_MyExecutor().execute(() -> System.out.print("hello Executor"));
    }
}
