package strom.threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class D6_ParallelComputing {

    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        List<Integer> sum = getPrime(1, 300000);
        long end = System.currentTimeMillis();
        System.out.println("for: " + sum.size());
        System.out.println(end - start);


        ExecutorService executorService = Executors.newFixedThreadPool(4);
        MyTask t1 = new MyTask(1, 80000);
        MyTask t2 = new MyTask(80001, 160000);
        MyTask t3 = new MyTask(160001, 240000);
        MyTask t4 = new MyTask(240001, 300000);

        Future<List<Integer>> f1 = executorService.submit(t1);
        Future<List<Integer>> f2 = executorService.submit(t2);
        Future<List<Integer>> f3 = executorService.submit(t3);
        Future<List<Integer>> f4 = executorService.submit(t4);

        start = System.currentTimeMillis();
        List<Integer> result = new ArrayList<>();
        result.addAll(f1.get());
        result.addAll(f2.get());
        result.addAll(f3.get());
        result.addAll(f4.get());
        end = System.currentTimeMillis();
        System.out.println("parallel: " + result.size());
        System.out.println(end - start);
        // shutdown
    }

    static class MyTask implements Callable<List<Integer>> {
        int start, end;
        MyTask(int s, int e) {
            this.start = s;
            this.end = e;
        }

        @Override
        public List<Integer> call() throws Exception {
           return getPrime(start, end);
        }
    }
    static boolean isPrime(int num) {
        for (int i = 2; i <= num/2 ; i++) {
            if(num % i == 0) return false;

        }
        return true;
    }

    static List<Integer> getPrime(int s, int e) {
        List<Integer> result = new ArrayList<>();
        for (int i = s; i <= e; i++) {
            if(isPrime(i)) result.add(i);
        }
        return result;
    }
}
