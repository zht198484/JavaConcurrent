package com.zht.concurrent.producer.consumer.disruptor;

import java.util.concurrent.atomic.AtomicInteger;

import com.lmax.disruptor.WorkHandler;

public class Consumer implements WorkHandler<MyEvent>{
	
	private String consumerId;
	
	private static AtomicInteger count = new AtomicInteger(0);
	
	Consumer(String consumerId){
		this.consumerId = consumerId;
	}

	@Override
	public void onEvent(MyEvent event) throws Exception {
		System.out.println("Consumer id: " + this.consumerId + "，event id：" + event.getId());
		count.incrementAndGet();
	}
	
	int getCount(){
		return count.get();
	}

}
