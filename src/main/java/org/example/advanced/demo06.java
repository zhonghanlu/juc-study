package org.example.advanced;

import java.util.concurrent.TimeUnit;

public class demo06 {

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {

            for (int i = 0; i < 300; i++) {
                System.out.println(Thread.currentThread().getName() + "运行中" + i);
            }

            System.out.println("当前中断状态 02：" + Thread.currentThread().isInterrupted());

        }, "t1");

        t1.start();

        t1.interrupt();

        System.out.println("当前中断状态  01：" + t1.isInterrupted());

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("当前中断状态  03：" + t1.isInterrupted());
    }

}
