package org.example.base;

public class demo01 {
    public static void main(String[] args) {

        Thread aa = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "::" + Thread.currentThread().isDaemon());
            while (true) {

            }
        }, "aa");

        aa.setDaemon(true);  //设置守护线程    没用户线程了，jvm会结束

        aa.start();

        System.out.println(Thread.currentThread().getName() + " over");

    }
}
