package com.woccupyl.lib.test;



public class MyPublisher {
	
	
	MyEventBus eventBus=MyEventBus.instance;
	
	
	public void publish() throws Exception{
		
	
		 boolean test = com.woccupyl.lib.test.Constant.Version > 12;
		
		eventBus.post(new MyEventTwo());
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		MyPublisher publisher=new MyPublisher();
		MyRegister register=new MyRegister();
		register.init();
		try {
			publisher.publish();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
