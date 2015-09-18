package example.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 *动态代理:java提供了Proxy类来实现在运行期间创建对象的功能.
 *Proxy类
 * http://www.infoq.com/cn/articles/cf-java-reflection-dynamic-proxy
 * 
 * @author Administrator
 *
 */
public class StudyProxy implements StudyProxyInterface{

	@Override
	public void study() {
		// TODO Auto-generated method stub
		System.out.println("study ....");
	}
	
	/**
	 * 动态代理功能实现：JDK的动态代理基于接口实现的
	 * 1:定义一个接口
	 * 2：实现一个接口的实现类
	 * 3：实现InvocationHandler类,
	 * 4:调用Proxy.newProxyInstance()方法生成一个代理类.
	 * 这个的代理类实现了接口的所有方法
	 * InvocationHandler实现增强功能
	 * @param args
	 */
	public static void main(String[] args) {
		
		StudyProxy study = new StudyProxy();
		StudyProxyInterface proxy = (StudyProxyInterface) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), 
				new Class[]{StudyProxyInterface.class}, new StudyProxyInvocationHandler(study));
		proxy.study();
	}
}
