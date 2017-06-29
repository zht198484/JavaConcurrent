package com.zht.concurrent.countdown.latch;

import java.util.concurrent.CountDownLatch;

/**
 * Created by zht198484 on 2017/6/29.
 * Demo for CountDownLatch
 */
public class CountDownLatchTest {
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(3);

        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                String name = Thread.currentThread().getName();
                System.out.println(name + " is waiting another 3 thread is done.");
                try {
                    countDownLatch.await();
                    System.out.println("Another 3 thread is done and lets start this thread "+name);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " is done.");
                countDownLatch.countDown();
            }).start();
        }




    }
}
