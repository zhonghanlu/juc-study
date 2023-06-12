package org.example.advanced;

import java.util.concurrent.*;

public class demo01 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        FutureTask<String> futureTask1 = new FutureTask<>(() -> {
            TimeUnit.MILLISECONDS.sleep(500);
            return "task1 over";
        });

        threadPool.submit(futureTask1);

        System.out.println(futureTask1.get());

        threadPool.shutdown();
    }

}
