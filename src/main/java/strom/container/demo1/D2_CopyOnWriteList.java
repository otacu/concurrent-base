package strom.container.demo1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;


/**
 * 写时复制：每次写时，都会把原来的容器复制出来，写完后再指向新的容易，读的时候不用加锁效率特高！！
 *
 *  缺点：1、适用于特定场景 2、占内存，每次写都要复制一份  3、存在数据一致性问题
 */
public class D2_CopyOnWriteList {
    public static void main(String[] args) {
       // List list = new ArrayList<>();
       // List list = new Vector();
        List list = new CopyOnWriteArrayList();
      //  List list = new ArrayList<>();
            //一个没有加锁的容器，包装一下返回成一个加锁的容器
        // List<Object> list1 = Collections.synchronizedList(new ArrayList<>());
    }
}
