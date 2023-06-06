package org.example.base;


import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

class MyTask extends RecursiveTask<Integer> {

    private final Integer VALUE = 10;

    private Integer begin;

    private Integer end;

    private Integer result = 0;

    public MyTask(Integer begin, Integer end) {
        this.begin = begin;
        this.end = end;
    }

    @Override
    protected Integer compute() {

        if (end - begin <= VALUE) {
            for (Integer i = begin; i <= end; i++) {
                result = result + i;
            }
        } else {

            int middle = (begin + end) / 2;

            MyTask myTask1 = new MyTask(begin, middle);

            MyTask myTask2 = new MyTask(middle + 1, end);

            myTask1.fork();

            myTask2.fork();

            result = myTask1.join() + myTask2.join();
        }
        return result;
    }
}

public class ForkJoinDemo {

    public static void main(String[] args) {
        MyTask myTask = new MyTask(0, 100);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Integer> task = forkJoinPool.submit(myTask);
        try {
            System.out.println(task.get());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } finally {
            forkJoinPool.shutdown();
        }
    }
}
