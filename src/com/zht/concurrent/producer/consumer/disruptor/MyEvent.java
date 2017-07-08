package com.zht.concurrent.producer.consumer.disruptor;


public class MyEvent {

	private String id;
	private String name;
	private double price;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(double v) {
		this.price = v;
	}

	public double getPrice() {
		return this.price;
	}
}