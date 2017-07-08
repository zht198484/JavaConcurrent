package com.zht.concurrent.producer.consumer.disruptor.simple;

import com.lmax.disruptor.*;
import com.lmax.disruptor.dsl.ProducerType;
import com.zht.concurrent.producer.consumer.disruptor.MyEvent;

import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;

public class DisruptorTest {

    public static void main(String[] args) throws Exception {

        RingBuffer<MyEvent> ringBuffer =
                RingBuffer.create(ProducerType.MULTI,
                        MyEvent::new,
                        1024 * 1024,
                        new YieldingWaitStrategy());

        SequenceBarrier barriers = ringBuffer.newBarrier();

        Consumer[] consumers = new Consumer[3];
        for (int i = 0; i < consumers.length; i++) {
            consumers[i] = new Consumer("c" + i);
        }

        WorkerPool<MyEvent> workerPool =
                new WorkerPool<>(ringBuffer,
                        barriers,
                        new IntEventExceptionHandler(),
                        consumers);

        ringBuffer.addGatingSequences(workerPool.getWorkerSequences());
        workerPool.start(Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()));

        final CountDownLatch latch = new CountDownLatch(1);
        for (int i = 0; i < 100; i++) {
            final Producer p = new Producer(ringBuffer);
            new Thread(() -> {
                try {
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int j = 0; j < 100; j++) {
                    p.produceData(UUID.randomUUID().toString());
                }
            }).start();
        }
        Thread.sleep(2000);
        System.out.println("---------------Start producing-----------------");
        latch.countDown();
        Thread.sleep(5000);
        System.out.println("Total count:" + consumers[0].getCount());
    }

    static class IntEventExceptionHandler implements ExceptionHandler<MyEvent> {
        public void handleEventException(Throwable ex, long sequence, MyEvent event) {
        }

        public void handleOnStartException(Throwable ex) {
        }

        public void handleOnShutdownException(Throwable ex) {
        }
    }
}
