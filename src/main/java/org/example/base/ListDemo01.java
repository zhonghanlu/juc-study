package org.example.base;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class ListDemo01 {
    public static void main(String[] args) {
//        List<String> list = new ArrayList<>();

        //使用vector 解决
//        List<String> list = new Vector<>();

        //使用Collections.synchronizedList 解决
//        List<String> list = Collections.synchronizedList(new ArrayList<>());

        //使用copyOnWrite 解决  实际就是上锁   每个写复制一份   浪费性能
//        List<String> list = new CopyOnWriteArrayList<>();
//
//        for (int i = 0; i < 30; i++) {
//            new Thread(() -> {
//                list.add(UUID.randomUUID().toString().substring(0, 8));
//
//                System.out.println(list);
//            }, String.valueOf(i)).start();
//        }

        Set<String> set = new HashSet<>();

        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0, 8));

                System.out.println(set);
            }, String.valueOf(i)).start();
        }


        //略  ConcurrentHashMap 写法
    }


}
