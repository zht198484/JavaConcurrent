package com.zht.concurrent.producer.consumer.disruptor;


public class MyEvent {

	private String id;
	private String name;

	String getId() {
		return id;
	}

	void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}