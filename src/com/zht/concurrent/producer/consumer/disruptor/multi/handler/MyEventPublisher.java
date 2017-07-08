package com.zht.concurrent.producer.consumer.disruptor.multi.handler;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

import com.lmax.disruptor.EventTranslator;
import com.lmax.disruptor.dsl.Disruptor;
import com.zht.concurrent.producer.consumer.disruptor.MyEvent;

public class MyEventPublisher implements Runnable {  
	
    private Disruptor<MyEvent> disruptor;
    private CountDownLatch latch;  
    
    private static int LOOP=10;
  
    MyEventPublisher(CountDownLatch latch, Disruptor<MyEvent> disruptor) {
        this.disruptor=disruptor;  
        this.latch=latch;  
    }  
  
    @Override  
    public void run() {  
    	MyEventEventTranslator MyEventTransloator = new MyEventEventTranslator();  
        for(int i=0;i<LOOP;i++){  
            disruptor.publishEvent(MyEventTransloator);  
        }  
        latch.countDown();  
    }  
      
}  
  
class MyEventEventTranslator implements EventTranslator<MyEvent>{  
    
	private Random random=new Random();  
    
	@Override  
    public void translateTo(MyEvent event, long sequence) {
        event.setPrice(random.nextDouble()*9999);
    }  

}  