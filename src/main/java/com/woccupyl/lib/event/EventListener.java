package com.woccupyl.lib.event;

import java.lang.reflect.Method;

public class EventListener {
	
	private boolean activated=true;
	
	private Method method;
	
	private int Order;
	
	private boolean throwException=true;
	
	private Object instance;

	public Method getMethod() {
		return method;
	}

	public void setMethod(Method method) {
		this.method = method;
	}

	public int getOrder() {
		return Order;
	}

	public void setOrder(int order) {
		Order = order;
	}

	public boolean isThrowException() {
		return throwException;
	}

	public void setThrowException(boolean throwException) {
		this.throwException = throwException;
	}

	public Object getInstance() {
		return instance;
	}

	public void setInstance(Object instance) {
		this.instance = instance;
	}

	public boolean isActivated() {
		return activated;
	}

	public void setActivated(boolean activated) {
		this.activated = activated;
	}

}
