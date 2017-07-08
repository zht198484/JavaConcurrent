package com.zht.concurrent.producer.consumer.disruptor.multi.handler;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;
import com.zht.concurrent.producer.consumer.disruptor.MyEvent;

public class MyEventHandler5 implements EventHandler<MyEvent>,WorkHandler<MyEvent> {
	  
    @Override  
    public void onEvent(MyEvent event, long sequence, boolean endOfBatch) throws Exception {  
        this.onEvent(event);  
    }  
  
    @Override  
    public void onEvent(MyEvent event) throws Exception {  
    	System.out.println("handler5: get price : " + event.getPrice());
    	event.setPrice(event.getPrice() + 3.0);
    }  
}  