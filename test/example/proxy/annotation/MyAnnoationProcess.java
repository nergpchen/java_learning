package example.proxy.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class MyAnnoationProcess {

	public void process(){
		System.out.println("开始处理MyAnnotation");
	}
	
	public static void processAnnotations(Object o){
		Class clazz = o.getClass();
		Annotation a = clazz.getAnnotation(MyAnnotaion.class);
		if(a != null){
			System.out.println("类"+clazz.getName()+"有注解"+a.toString());
		}
		for(Method method :clazz.getMethods()){
		
		}
	}
}
