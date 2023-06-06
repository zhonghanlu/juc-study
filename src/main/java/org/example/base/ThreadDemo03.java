package org.example.base;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareResource {

    private int flag = 1;

    private Lock lock = new ReentrantLock();

    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();


    public void print5(int loop) throws InterruptedException {

        lock.lock();

        try {
            while (flag != 1) {
                c1.await();
            }

            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + "::打印了第：" + i + ":轮数：" + loop);
            }

            flag = 2;
            c2.signal();
        } finally {
            lock.unlock();
        }
    }

    public void print10(int loop) throws InterruptedException {

        lock.lock();

        try {
            while (flag != 2) {
                c2.await();
            }

            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + "::打印了第：" + i + ":轮数：" + loop);
            }

            flag = 3;
            c3.signal();
        } finally {
            lock.unlock();
        }
    }

    public void print15(int loop) throws InterruptedException {

        lock.lock();

        try {
            while (flag != 3) {
                c3.await();
            }

            for (int i = 0; i < 15; i++) {
                System.out.println(Thread.currentThread().getName() + "::打印了第：" + i + ":轮数：" + loop);
            }

            flag = 1;
            c1.signal();
        } finally {
            lock.unlock();
        }
    }


}


public class ThreadDemo03 {

    public static void main(String[] args) {
        ShareResource resource = new ShareResource();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    resource.print5(i);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "aa").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    resource.print10(i);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "bb").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    resource.print15(i);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "cc").start();
    }

}
