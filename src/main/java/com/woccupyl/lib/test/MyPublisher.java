package com.woccupyl.lib.test;



public class MyPublisher {
	
	MyEventBus eventBus;
	
	
	public void publish() throws Exception{
		
		
		eventBus.post(new MyEventTwo());
	}

}
