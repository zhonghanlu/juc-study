package org.example.base;

class Share {

    public int number = 0;

    public synchronized void add() throws InterruptedException {
        //1.判断
        while (number != 0) {
            this.wait();  //在哪睡 就在哪醒    在此处睡了，被其他线程叫醒了，直接往下执行  所以不能使用if
        }
        //2.干活
        number++;
        System.out.println(Thread.currentThread().getName() + "::" + number);
        //3.通知
        this.notifyAll();
    }


    public synchronized void de() throws InterruptedException {
        //1.判断
        while (number != 1) {
            this.wait();
        }
        //2.干活
        number--;
        System.out.println(Thread.currentThread().getName() + "::" + number);
        //3.通知
        this.notifyAll();
    }

}

public class addDe {

    public static void main(String[] args) {
        Share share = new Share();
        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    share.add();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "AA").start();

        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    share.de();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "BB").start();

        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    share.add();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "CC").start();

        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    share.de();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "DD").start();

    }

}
