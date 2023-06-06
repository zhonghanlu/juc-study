package org.example.base;

/**
 * 1.创建资源类   创建属性 和操作方法
 * 2.创建多线程 进行调用操作方法
 */

class Sale {

    public int number = 30;

    public synchronized void sale() {
        if (number > 0) {
            System.out.println(Thread.currentThread().getName() + ": 卖出：" + (number--) + "还剩：" + number);
        }
    }

}

public class Ticket {

    public static void main(String[] args) {
        Sale sale = new Sale();
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
