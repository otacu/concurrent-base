package strom.threadpool;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;
import java.util.concurrent.*;
import java.util.stream.Stream;

/**
 * // fork 分叉 join 合并s
 */
public class D10_ForkJoinPool {

    static int[] nums = new int[1000000];
    static final int MAX_NUM = 5000;
    static ThreadLocalRandom random = ThreadLocalRandom.current();

    static {
        for (int i = 0; i < nums.length; i++) {
            nums[i] = random.nextInt(1000);
        }

        // java8 流计算
        long start = System.currentTimeMillis();
        System.out.println(Arrays.stream(nums).sum());
        long end = System.currentTimeMillis();
        System.out.println("stream:" + (end -start));

        start = System.currentTimeMillis();
        System.out.println(Arrays.stream(nums).parallel().sum());
        end = System.currentTimeMillis();
        System.out.println("parallelStream:" + (end -start));
    }

    // 递归函数
    @Data
    @AllArgsConstructor
    static class MyTask extends RecursiveAction {

        int start, end;

        @Override
        protected void compute() {
            if (end - start <= MAX_NUM) {
                long sum = 0L;
                for (int i = start; i < end; i++) {
                    sum += nums[i];
                }
                System.out.println(String.format("from %s to %s = %s", start, end, sum));
            } else {
                int middle = start + (end - start) /2;
                MyTask subTask1 = new MyTask(start, middle);
                MyTask subTask2 = new MyTask(middle, end);
                subTask1.fork();
                subTask2.fork();
            }
        }
    }

    // 递归函数
    @Data
    @AllArgsConstructor
    static class MyTask2 extends RecursiveTask<Long> {

        int start, end;

        @Override
        protected Long compute() {
            if (end - start <= MAX_NUM) {
                long sum = 0L;
                for (int i = start; i < end; i++) {
                    sum += nums[i];
                }
//                System.out.println(String.format("from %s to %s = %s", start, end, sum));
                return sum;
            } else {
                int middle = start + (end - start) /2;
                MyTask2 subTask1 = new MyTask2(start, middle);
                MyTask2 subTask2 = new MyTask2(middle, end);
                subTask1.fork();
                subTask2.fork();

                return subTask1.join() + subTask2.join();
            }
        }
    }

    public static void main(String[] args) throws  Exception {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
//        MyTask task = new MyTask(0, nums.length);
//        forkJoinPool.execute(task);
//        // ForkJoinTask是精灵线程，所以主程序需要阻塞才能看到输出
//        System.in.read();

        MyTask2 task2 = new MyTask2(0, nums.length);

        long start = System.currentTimeMillis();
        forkJoinPool.execute(task2);
        System.out.println(task2.join());
        long end = System.currentTimeMillis();
        System.out.println("forkjoin:" + (end -start));
    }
}
