package com.woccupyl.lib.test;

import com.woccupyl.lib.event.Subscribor;
import com.woccupyl.lib.event.Subscribor.Errors;


public class MyRegister {
	
	public MyEventBus eventBus;
	
	public void init() {
		
		eventBus.register(this);
	}

	
	@Subscribor(order=1)
	public void onMyEvent0(testEvent event) throws Exception{
		
		System.out.println("has entered the method 1  !");
		
		//throw new Exception("jump out of the event!");
		
	}
	
	@Subscribor(order=2,error=Errors.Catch)
	public void onMyEvent1(MyEvent event) throws Exception{
		
		System.out.println("has entered the method 2 !");
		
		throw new Exception("jump out of the event!");
		
	}

	@Subscribor(order=12)
	public void onMyEvent(MyEvent event) throws Exception{
		
		System.out.println("has entered the method 12!");
	}
	
}
