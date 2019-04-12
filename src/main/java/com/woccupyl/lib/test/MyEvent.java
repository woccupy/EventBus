package com.woccupyl.lib.test;

public class MyEvent {
	
	
	private String name;
	
	private String arg;
	
	private TestObject testObject;
	
	private Boolean hasName=false;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getArg() {
		return arg;
	}

	public void setArg(String arg) {
		this.arg = arg;
	}

	public Boolean getHasName() {
		return hasName;
	}

	public void setHasName(Boolean hasName) {
		this.hasName = hasName;
	}

	public TestObject getTestObject() {
		return testObject;
	}

	public void setTestObject(TestObject testObject) {
		this.testObject = testObject;
	}


	

}
