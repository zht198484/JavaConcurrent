package com.zht.concurrent.producer.consumer.disruptor;

import com.lmax.disruptor.RingBuffer;

/**
 * producer to produce order
 */
class Producer {

	private final RingBuffer<MyEvent> ringBuffer;
	
	Producer(RingBuffer<MyEvent> ringBuffer){
		this.ringBuffer = ringBuffer;
	}
	
	void produceData(String data){
		long sequence = ringBuffer.next();
		try {
			MyEvent order = ringBuffer.get(sequence);
			order.setId(data);
		} finally {
			ringBuffer.publish(sequence);
		}
	}
	
	
}
