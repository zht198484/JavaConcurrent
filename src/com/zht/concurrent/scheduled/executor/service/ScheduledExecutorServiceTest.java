package com.zht.concurrent.scheduled.executor.service;

import java.util.concurrent.*;

/**
 * Created by zht198484 on 2017/6/28.
 * Demo for main apis of scheduled executor service
 */
public class ScheduledExecutorServiceTest {
    public static void main(String[] args) throws Exception {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors());
        ScheduledFuture<String> schedule = scheduledExecutorService.schedule(() -> "callable a is done.", 4, TimeUnit.SECONDS);

        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = (ScheduledThreadPoolExecutor) scheduledExecutorService;
        BlockingQueue<Runnable> runnableBlockingQueue = scheduledThreadPoolExecutor.getQueue();
        System.out.println(runnableBlockingQueue.size());
        for (Runnable runnable :
                runnableBlockingQueue) {
            System.out.println(runnable);
        }
//        System.out.println(scheduledThreadPoolExecutor.getQueue().take());
        System.out.println(schedule.get());
        scheduledThreadPoolExecutor.scheduleAtFixedRate(() -> System.out.println("callable a is done."), 0, 1, TimeUnit.SECONDS);
        System.out.println(scheduledThreadPoolExecutor.getContinueExistingPeriodicTasksAfterShutdownPolicy());
        scheduledThreadPoolExecutor.setContinueExistingPeriodicTasksAfterShutdownPolicy(true);
        System.out.println(scheduledThreadPoolExecutor.getContinueExistingPeriodicTasksAfterShutdownPolicy());

        scheduledThreadPoolExecutor.schedule(() -> scheduledThreadPoolExecutor.setContinueExistingPeriodicTasksAfterShutdownPolicy(false), 4, TimeUnit.SECONDS);

        scheduledThreadPoolExecutor.shutdown();



    }
}
