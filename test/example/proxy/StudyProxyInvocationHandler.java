package example.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class StudyProxyInvocationHandler implements InvocationHandler{
	
	Object target ; 
	
	public StudyProxyInvocationHandler(Object target){
		this.target= target;
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		// TODO Auto-generated method stub
		System.out.println("开始");
		method.invoke(target, args);
		System.out.println("结束");
		return null;
	}

}
