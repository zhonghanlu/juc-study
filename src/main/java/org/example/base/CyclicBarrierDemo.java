package org.example.base;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {


    private static final int NUMBER = 7;

    public static void main(String[] args) {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> {
            System.out.println("等待完成，end");
        });

        for (int i = 0; i < 7; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+":执行完成");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (BrokenBarrierException e) {
                    throw new RuntimeException(e);
                }
            },String.valueOf(i)).start();
        }

    }


}
