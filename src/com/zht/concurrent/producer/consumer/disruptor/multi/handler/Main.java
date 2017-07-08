package com.zht.concurrent.producer.consumer.disruptor.multi.handler;

import com.lmax.disruptor.BusySpinWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.zht.concurrent.producer.consumer.disruptor.MyEvent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {  
    public static void main(String[] args) throws InterruptedException {  
       
    	long beginTime=System.currentTimeMillis();
        int bufferSize=1024;  
        ExecutorService executor=Executors.newFixedThreadPool(8);  

        Disruptor<MyEvent> disruptor = new Disruptor<>(MyEvent::new, bufferSize, executor, ProducerType.SINGLE, new BusySpinWaitStrategy());
        

        MyEventHandler1 h1 = new MyEventHandler1();
        MyEventHandler2 h2 = new MyEventHandler2();
        MyEventHandler3 h3 = new MyEventHandler3();
        MyEventHandler4 h4 = new MyEventHandler4();
        MyEventHandler5 h5 = new MyEventHandler5();
        disruptor.handleEventsWithWorkerPool(h1,h2);
        disruptor.after(h1).handleEventsWith(h4);
        disruptor.after(h2).handleEventsWith(h5);
        disruptor.after(h4, h5).handleEventsWith(h3);

        
        
        disruptor.start();
        CountDownLatch latch=new CountDownLatch(1);  
        executor.submit(new MyEventPublisher(latch, disruptor));
        
        latch.await();
       
        disruptor.shutdown();  
        executor.shutdown();  
        System.out.println("总耗时:"+(System.currentTimeMillis()-beginTime));  
    }  
}  