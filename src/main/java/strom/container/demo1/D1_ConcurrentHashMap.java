package strom.container.demo1;

import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;


/**
 * Hashtable、Collections.synchronizedMap : 线程安全，重量级锁，性能低，每次操作对整个容器加锁
 *
 * ConcurrentHashMap:线程安全，效率高，JDK7:分段锁称为Segment，将容器分成16块，每次操作只会锁住16块中之一，锁粒度更小了；
 *                  同时其他块也可以操作，效率更高了，
 *                  JDK8: 红黑树 +CAS +synchronized
 *
 *  http://www.importnew.com/22007.html
 */
public class D1_ConcurrentHashMap {

    public static void main(String[] args) throws Exception {
        Map<String, String> map = new ConcurrentHashMap<>();

//        Map<String, String> map = new ConcurrentSkipListMap<>(); // 高并发 +排序
//
//        Map<String, String> map = new HashMap<>();
//        Map<String, String> map = new TreeMap<>();

//        Map<String, String> map = new Hashtable<>();
//        Map<String, String> map = Collections.synchronizedMap(new HashMap<>());
        ThreadLocalRandom random = ThreadLocalRandom.current();

        Thread[] ths = new Thread[100];
        CountDownLatch latch = new CountDownLatch(ths.length);
        long start = System.currentTimeMillis();
        for (int i = 0; i <ths.length ; i++) {
            ths[i] = new Thread(() -> {
                for (int j=0; j<10000; j++)         {
                    map.put("key" +random.nextInt(1000), "value" +random.nextInt(1000));
                }

            } );
            latch.countDown();
        }

        Stream.of(ths).forEach(s -> s.start());
        latch.await();

        long end = System.currentTimeMillis();
        System.out.println(end - start);


    }
}
