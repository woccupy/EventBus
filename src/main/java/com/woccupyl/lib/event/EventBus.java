package com.woccupyl.lib.event;

import java.lang.reflect.Method;

import com.woccupyl.lib.event.Subscriber.Errors;


public class EventBus {
	
	private EventContainer eventContainer=new EventContainer();
	
	public void register(Object instance){
		 Class<?> clazz = instance.getClass();
		    for (Method method : EventBusUtils.getAnnotatedMethodsNotCached(clazz)) {
		      Class<?>[] parameterTypes = method.getParameterTypes();
		      Subscriber annotation= method.getAnnotation(Subscriber.class);
		      Class<?> eventType = parameterTypes[0];
		      EventListener listener=new EventListener();
		      listener.setOrder(annotation.order());
		      listener.setInstance(instance);
		      listener.setMethod(method);
		      listener.setCondition(annotation.condition());
		      listener.setThrowException(annotation.error()==Errors.Throw);
		      eventContainer.addEvent(eventType, listener);
		    }
	}
	
	public void cancel(Object instance) {
		eventContainer.cancel(instance);
	}
	
	public void post(Object event) throws Exception {
		eventContainer.post(event);
	}
}
