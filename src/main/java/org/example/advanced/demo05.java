package org.example.advanced;

import java.util.concurrent.TimeUnit;

public class demo05 {

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println(Thread.currentThread().getName() + "中断了......");
                    break;
                }
                System.out.println(Thread.currentThread().getName() + "运行中====");
            }

        }, "t1");
        t1.start();

        try {TimeUnit.MILLISECONDS.sleep(500);} catch (InterruptedException e) {throw new RuntimeException(e);}

        new Thread(t1::interrupt, "t2").start();
    }

}
