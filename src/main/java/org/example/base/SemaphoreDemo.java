package org.example.base;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreDemo {


    public static void main(String[] args) {

        Semaphore semaphore = new Semaphore(3);

        for (int i = 0; i < 6; i++) {

            new Thread(() -> {

                try {
                    semaphore.acquire();

                    System.out.println(Thread.currentThread().getName() + "： 抢占到了");

                    TimeUnit.SECONDS.sleep(new Random().nextInt(5));

                    System.out.println(Thread.currentThread().getName() + "： 离开了");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }finally {
                    semaphore.release();
                }
            }, String.valueOf(i)).start();
        }
    }


}
