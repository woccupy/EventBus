package com.woccupyl.lib.event;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class EventBusUtils {
	
  static public List<Class<?>> getAllClasses(Class<?> clazz){
		
		List<Class<?>> result=new ArrayList<>();
		
		while (clazz!=null) {
			result.add(clazz);
			clazz=clazz.getSuperclass();
		}
		return result;
	}
  
  public static List<Method> getAnnotatedMethodsNotCached(Class<?> clazz) {

		List<Method> result = new ArrayList<Method>();
		
		List<Class<?>> classList= getAllClasses(clazz);
		
		for (Class<?> supertype : classList) {
			for (Method method : supertype.getDeclaredMethods()) {
				if (method.isAnnotationPresent(Subscribor.class) && !method.isSynthetic()) {
					Class<?>[] parameterTypes = method.getParameterTypes();
					if (parameterTypes.length != 1) {
						// invalid parameters!
						continue;
					}
					result.add(method);
				}
			}
		}
		return result;
	}

}
