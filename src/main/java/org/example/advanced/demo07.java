package org.example.advanced;

import java.util.concurrent.TimeUnit;

public class demo07 {

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {

            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("程序中断" + Thread.currentThread().isInterrupted());
                    break;
                }
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                System.out.println("heihei");
            }

        }, "t1");

        t1.start();

        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        new Thread(t1::interrupt, "t2").start();

    }

}
