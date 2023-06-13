package org.example.advanced;

import java.util.concurrent.TimeUnit;

public class demo04 {

    static volatile boolean flag = false;

    public static void main(String[] args) {

        new Thread(() -> {
            while (true) {
                if (flag) {
                    System.out.println(Thread.currentThread().getName() + "被中止");
                    break;
                }
                System.out.println("t1  运行中");
            }
        }, "t1").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        new Thread(() -> {
            flag = true;
        }, "t2").start();

    }

}
