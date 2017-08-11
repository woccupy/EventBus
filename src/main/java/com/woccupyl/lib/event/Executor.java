package com.woccupyl.lib.event;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Executor {
	
	private static Logger logger = LoggerFactory.getLogger(Executor.class);
	
	public void execute(EventListener listener, Object event) throws Exception {
		
		if(listener.isThrowException()){
			execThrowException(listener,event);
		} else {
			execCatchException(listener,event);
		}
	}
	
	
	private void execThrowException(EventListener listener, Object event) throws Exception{

		this.exec(listener, event);
	}
	
    private void execCatchException(EventListener listener, Object event){
    	
    	try {
    		this.exec(listener, event);
		} catch (Exception e) {
			logger.error("register's method has error!",e);
		}
	}
    
    private void exec(EventListener listener, Object event) throws Exception {
    	
         Method method=listener.getMethod();
         try {
			method.invoke(listener.getInstance(), event);
		} catch (IllegalAccessException e) {
			logger.error("Access Error:",e);
		} catch (IllegalArgumentException e) {
			logger.error("Auguments Error:",e);
		} catch (InvocationTargetException e) {
			
			Throwable question= e.getTargetException();
			if(question instanceof Exception){
				throw (Exception)question;
			}
			else {
				logger.error("Target error:",e);
			}
		}
	}
	

}
