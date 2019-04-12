package com.woccupyl.lib.event;

import java.lang.reflect.Method;

import org.springframework.expression.Expression;
import org.springframework.expression.spel.SpelCompilerMode;
import org.springframework.expression.spel.SpelParserConfiguration;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.util.StringUtils;

public class EventListener {
	
	private boolean activated=true;
	
	private Method method;
	
	private int Order;
	
	private boolean throwException=true;
	
	private boolean hasCondition=false;
	
	private Object instance;
	
	private String condition="";
	
	private Expression expr;

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

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
		
		if(!StringUtils.isEmpty(condition))
			this.hasCondition=true;
		this.compileCondition();
	}
	
	
	protected void compileCondition() {
		
		if(this.hasCondition==false)
			return;
		SpelParserConfiguration config = new SpelParserConfiguration(SpelCompilerMode.IMMEDIATE,
			    this.getInstance().getClass().getClassLoader());
		SpelExpressionParser parser = new SpelExpressionParser(config);
		expr = parser.parseExpression(this.condition);
		
	}
	
	
    public boolean matchedCondition(Object event) {
    	
    	if(this.hasCondition==false) 
    		return true;
    	 StandardEvaluationContext societyContext = new StandardEvaluationContext(event);
    	 return this.expr.getValue(societyContext,Boolean.class);
    }

}
