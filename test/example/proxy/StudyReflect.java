package example.proxy;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Test;

/** 
 * java反射学习
 * 反射性能：http://stackoverflow.com/questions/435553/java-reflection-performance
 * http://www.infoq.com/cn/articles/cf-java-reflection-dynamic-proxy
 * http://docs.oracle.com/javase/tutorial/reflect/index.html
 * 1:反射机制：java的反射机制是指可以在程序运行的时候，动态的获取类的内部结构，包括类的名称，类型，类的属性，方法和注解的一系列信息，并且可以在程序运行的时候，动态的执行程序的方法的
 * 一种机制，那么这种机制可以用来实现AOP和IOC等功能.
 * 反射机制如何实现的？
 * 1：首先java和反射机制有关的类都在在java.lang.reflect包,这个包中有24个类.
 * 在运行的时候，怎么知道类的信息呢？我们猜测可能的一个是在字节码文件中已经记录了类的内部结构信息，我们可以想象，在生成一个.class文件的时候，我们可以先把 类的信息以一定的规则和格式写入到文件中。
 * 那么在类加载器读取.class文件的时候，就会保存类的信息，如果要读取类的信息，则就知道了类的内部结构。
 * 2：如何在运行的时候，生成一个类的对象呢？
 * Class.newInstance()方法来生成一个类的实例，相当于new 一个
 * 反射机制缺点：
 * Reflection is powerful, but should not be used indiscriminately. If it is possible to perform an operation without using reflection, then it is preferable to avoid using it. The following concerns should be kept in mind when accessing code via reflection.
 * 反射机制非常的有用，但是不能够随便用。  如果可以不用反射机制，尽量不用反射机制。
 * 1：Performance Overhead（性能的消耗） 
 * 反射包括了一些动态类型，所以JVM无法对这些代码进行优化。因此，反射操作的效率要比那些非反射操作低得多。我们应该避免在经常被 执行的代码或对性能要求很高的程序中使用反射
 * 2：Security Restrictions安全限制
 *  反射必须在一个没有安全限制的环境中运行，所以如果在一个有安全限制的环境中运行，则就不能使用。
 *  Exposure of Internals ：内部曝露
 *   由于反射允许代码执行一些在正常情况下不被允许的操作（比如访问私有的属性和方法），所以使用反射可能会导致意料之外的副作用－－代码有功能上的错误，降低可移植性。反射代码破坏了抽象性，因此当平台发生改变的时候，代码的行为就有可能也随着变化
 *涉及到核心概念和基本概念有哪些？
 *Class对象用来存储类的信息，比如String类的成员，方法等内部结构都是用Class一个对象表示。
 *Method:
 *   
 * @author Administrator
 *
 */
public class StudyReflect {
	
	/**
	 * 获取java类的内部结构:包括声明，成员和方法等内部结构.
	 */
	@Test
	public  void getClassInfo() {
		Class classs = String.class;
		Annotation[]  annotaions = classs.getAnnotations();
		String canonicalName = classs.getCanonicalName();
		System.out.println(annotaions);
		System.out.println(canonicalName);
		System.out.println(classs.getClassLoader());
		System.out.println(classs.getConstructors());
		System.out.println(classs.getDeclaredAnnotations());
		
		Field[] fields = classs.getDeclaredFields();
		
		Method[] methods =classs.getDeclaredMethods();

		System.out.println("an ...... ");
		for(Annotation an : annotaions){
			System.out.println(an);
		}
		
		System.out.println("fields ...... ");
		for(Field field : fields){
			System.out.println(field);
		}
		System.out.println("method ...... ");
		for(Method method : methods){
			System.out.println(method.getName());
		}
	}	
	@Test
	public void testInvoke(){
		String name  = "chenxiong";
		Class classe = name.getClass();
		try {
			Method method = classe.getMethod("toString", null);
			Object result = method.invoke(name, null);
			System.out.println(result);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Test
	/**
	 * 用来测试反射的性能:
	 * doRegular:大概是20-30ms;而doRegular()执行的时间近1秒钟，所用用反射来生成一个对象的时间是非常慢的。
	 * 
	 */
	public void testPerformance(){
		doRegular();
		doReflection();
	}
	
	public void doRegular(){
		long start = System.currentTimeMillis();
		for (int i=0;i<1000000;i++){
			String a = new String("s");
			a.toCharArray();
		}
		 System.out.println(System.currentTimeMillis() - start);
		
	}
	
	public void doReflection(){
		long start = System.currentTimeMillis();
		for (int i=0;i<1000000;i++){
			String a;
			try {
				//Class c = Class.forName("java.lang.String", false, Thread.currentThread().getContextClassLoader());
				Class c = Class.forName("java.lang.String");
				
				c.newInstance();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		 System.out.println(System.currentTimeMillis() - start);
	}
}
