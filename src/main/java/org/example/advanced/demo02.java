package org.example.advanced;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class demo02 {

    public static void main(String[] args) {

        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        try {
            CompletableFuture.supplyAsync(() -> {
                System.out.println(Thread.currentThread().getName() + "come in");
                int result = new Random().nextInt(10);
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                return result;
            }, threadPool).whenComplete((v, e) -> {
                if (null == e) {
                    System.out.println("执行更新操作val：" + v);
                }
            }).exceptionally(e -> {
                System.out.println("程序异常：" + e.getCause());
                return null;
            });

            System.out.println(Thread.currentThread().getName() + "主线程结束");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            threadPool.shutdown();
        }

    }

}
