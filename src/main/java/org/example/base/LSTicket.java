package org.example.base;

import java.util.concurrent.locks.ReentrantLock;

class LSale {

    public int number = 30;

    private final ReentrantLock lock = new ReentrantLock();

    public void sale() {
        lock.lock();
        try {
            if (number > 0) {
                System.out.println(Thread.currentThread().getName() + ": 卖出：" + (number--) + "还剩：" + number);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

}

public class LSTicket {

    public static void main(String[] args) {
        LSale sale = new LSale();
        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                sale.sale();
            }
        }, "aa").start();

        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                sale.sale();
            }
        }, "bb").start();

        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                sale.sale();
            }
        }, "cc").start();

    }

}
