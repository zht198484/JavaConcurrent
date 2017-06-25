package com.zht.thread.communication.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Created by zht198484 on 2017/6/25.
 * Demo for main apis of Semaphore.
 */
public class SemaphoreTest {
    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(10);
        semaphore.acquire();
        semaphore.acquire();
        semaphore.acquire();
        semaphore.acquire();
        System.out.println(semaphore.availablePermits());
        semaphore.release(6);
        System.out.println(semaphore.availablePermits());

        semaphore.drainPermits();
        System.out.println(semaphore.availablePermits());

        semaphore.tryAcquire(2,2000, TimeUnit.MILLISECONDS);

        semaphore.release(2);

        semaphore.acquireUninterruptibly();

        semaphore.getQueueLength();

        semaphore.hasQueuedThreads();

        semaphore = new Semaphore(1,true);

        boolean fair = semaphore.isFair();
        System.out.println(fair);

    }
}
