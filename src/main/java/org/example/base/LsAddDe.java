package org.example.base;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class LShare {

    private int number = 0;

    private Lock lock = new ReentrantLock();

    private Condition condition = lock.newCondition();

    public void add() throws InterruptedException {
        lock.lock();
        try {
            while (number != 0) {
                condition.await();
            }
            number++;
            System.out.println(Thread.currentThread().getName() + "::" + number);
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }


    public void de() throws InterruptedException {
        lock.lock();
        try {
            while (number != 1) {
                condition.await();
            }
            number--;
            System.out.println(Thread.currentThread().getName() + "::" + number);
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

}

public class LsAddDe {


    public static void main(String[] args) {
        LShare lShare = new LShare();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    lShare.add();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "AA").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    lShare.de();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "BB").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    lShare.add();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "CC").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    lShare.de();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "DD").start();
    }

}
