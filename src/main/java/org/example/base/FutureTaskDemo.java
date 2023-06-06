package org.example.base;


import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureTaskDemo {


    public static void main(String[] args) throws ExecutionException, InterruptedException {

        FutureTask futureTask = new FutureTask<>(() -> {

            System.out.println(Thread.currentThread().getName() + ": come in this");

            return 2048;
        });

        new Thread(futureTask, "lucy").start();

        while (!futureTask.isDone()) {
            System.out.println("wait....");
        }

        System.out.println(futureTask.get() + "第一次获取");

        System.out.println(futureTask.get() + "第二次获取");


    }


}
