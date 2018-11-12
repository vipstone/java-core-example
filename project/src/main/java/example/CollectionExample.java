package example;

import java.util.*;

// 集合示例
public class CollectionExample {

    public static void main(String[] args) {
//        Set set = new TreeSet();
//        set.add("orange");
//        set.add("apple");
//        set.add("banana");
//        set.add("grape");
//        set.add("banana");
//        System.out.println(set);


        Queue<String> queue =  new LinkedList<String>();
        queue.offer("a");
        queue.offer("b");
        queue.offer("c");
        queue.offer("d");
        System.out.println(queue);

        queue.poll();
        System.out.println(queue);

        queue.poll();
        queue.poll();
        queue.poll();

        System.out.println(queue.peek());
//        System.out.println(queue.element()); // element 查询失败会抛出异常
        System.out.println(queue);

    }

}
