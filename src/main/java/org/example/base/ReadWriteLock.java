package org.example.base;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class MyCache {


    private volatile Map<String, Object> map = new HashMap<>();

    private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();


    public void write(String key, Object val) throws InterruptedException {

        rwLock.writeLock().lock();

        System.out.println(Thread.currentThread().getName() + ":开始写" + val);

        map.put(key, val);

        TimeUnit.SECONDS.sleep(1);

        System.out.println(Thread.currentThread().getName() + ":写完了" + val);

        rwLock.writeLock().unlock();

    }

    public Object read(String key) {

        rwLock.readLock().lock();

        Object result = null;

        System.out.println(Thread.currentThread().getName() + ":开始读" + key);

        result = map.get(key);

        System.out.println(Thread.currentThread().getName() + ":读完了" + result);

        rwLock.readLock().unlock();

        return result;
    }


}


public class ReadWriteLock {


    public static void main(String[] args) {

        MyCache myCache = new MyCache();

        for (int i = 0; i < 5; i++) {
            final int num = i;
            new Thread(() -> {
                try {
                    myCache.write(num + "", num + "");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }, String.valueOf(i)).start();
        }

        for (int i = 0; i < 5; i++) {
            final int num = i;
            new Thread(() -> {
                myCache.read(num + "");
            }, String.valueOf(i)).start();
        }
    }

}







