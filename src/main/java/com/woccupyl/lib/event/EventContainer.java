package com.woccupyl.lib.event;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public  class EventContainer {
	
	private Object locker= new Object();
	
	private ConcurrentHashMap<Class<?>, List<EventListener>> eventDic=new ConcurrentHashMap<>();
	
	private Executor executor= new Executor();

	public void addEvent(Class<?> clazz, EventListener listener) {

		synchronized (locker) {

			List<EventListener> eventList = null;
			if (eventDic.containsKey(clazz) == false) {
				eventList = new ArrayList<EventListener>();
			} else {
				eventList = eventDic.get(clazz);
			}
			List<EventListener> copy = copyEventList(eventList);

			boolean inserted = false;
			for (int i = 0; i < copy.size(); i++) {

				if (copy.get(i).getOrder() >= listener.getOrder()) {
					copy.add(i, listener);
					inserted = true;
					break;
				}
			}

			if (inserted == false) {
				copy.add(listener);
			}
			eventDic.put(clazz, copy);
		}
	}
	
	@SuppressWarnings("unchecked")
	private List<EventListener> copyEventList(List<EventListener> eventList){
		
		if(eventList!=null){
			ArrayList<EventListener> eventArrayList=(ArrayList<EventListener>)eventList;
			ArrayList<EventListener> clonEventListeners=(ArrayList<EventListener>) eventArrayList.clone();
			return clonEventListeners;
		}
		return null;
	}
	
	public void cancel(Object instance) {
		synchronized (locker) {
			for (Map.Entry<Class<?>, List<EventListener>> entry : eventDic.entrySet()) {
				
				List<EventListener> listeners=entry.getValue();
				for(EventListener listener : listeners ){
					
					if(listener.getInstance().equals(instance)){
						listener.setActivated(false);
						listener.setInstance(null);
						listener.setMethod(null);
					}
				}

			}

		}
	}
	
	public void post(Object event) throws Exception {
		
		Class<?> clazz=event.getClass();
		List<Class<?>> classList=EventBusUtils.getAllClasses(clazz);
		Collections.reverse(classList);
		
		Class<?>[] interfaces= clazz.getInterfaces();
		for(Class<?> item : classList)
		{
			activeEvent(item, event);
		}
		
		for(Class<?> item : interfaces)
		{
			activeEvent(item, event);
		}
		
		
	}
	
	private void activeEvent(Class<?> clazz,Object event) throws Exception {
		
		if(eventDic.get(clazz)!=null){
			List<EventListener> eventList=eventDic.get(clazz);
			for(EventListener item :eventList){
				if(item.isActivated()==false)
					continue;
			     executor.execute(item, event);
			}
		}
		
	}
}
