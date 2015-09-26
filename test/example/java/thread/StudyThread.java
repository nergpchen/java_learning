package example.java.thread;

/**概念：
 一个线程是一个独立运行的调用序列,同时可以共享一些资源比如文件系统,并且也可以访问其他创建的对象。
 Java.lang.Thread类用来负责统计和控制线程的功能。
 每个程序至少有一个线程，一个线程用来运行main方法。另外一些backgroud线程在jvm初始化期间也会启动。
 
 A thread is a call sequence that executes independently of others, while at the same time possibly sharing underlying system resources such as files, 
 as well as accessing other objects constructed within the same program (see �1.2.2). 
 A java.lang.Thread object maintains bookkeeping and control for this activity.
Every program consists of at least one thread - the one that runs the main method of the class provided as a startup argument to the Java virtual machine ("JVM").
Other internal background threads may also be started during JVM initialization. The number and nature of such threads vary across JVM implementations. 
However, all user-level threads are explicitly constructed and started from the main thread, or from any other threads that they in turn create.
Here is a summary of the principal methods and properties of class Thread, as well as a few usage notes. They are further discussed and illustrated throughout this book. The JavaTM Language Specification ("JLS") and the published API documentation should be consulted for more detailed and authoritative descriptions.

 * 线程学习:
 * Thread是操作系统的一个概念，是计算机运行的最小单位，可以用来运行，一个进程可以拥有一个或者多个线程，
 * 线程可以共享父进程的资源。
 * 线程有自己的ID,内存资源和运算器。
 * CPU同时只能运行一个进程，但是可以同时运行多线程，也就是多线程并发运行。
 * 线程分类？
 * 线程属性？
 * 线程适用场景？
 * 如何实现线程？
 * @author Administrator
 *
 */
public class StudyThread extends Thread{

	int i =0;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		System.out.println(this.getId()+"i=="+i);
	}
	public static void testThread(){
		try {
		System.out.println("创建3个线程实例");
		StudyThread thread = new StudyThread();
		StudyThread thread2 = new StudyThread();
		StudyThread thread3 = new StudyThread();
		System.out.println("调用toString方法，显示线程信息");
		System.out.println(thread.toString());
		System.out.println(thread2.toString());
		System.out.println(thread3.toString());
		System.out.println("调用interrupt方法， 中断线程");
		thread.interrupt();
		
			System.out.println("调用sleep方法， ");
		
			thread.sleep(2000);
			thread.interrupted();
			System.out.println("启动线程：调用thread.start方法");
			thread.start();
			thread2.start();
			thread3.start();
			System.out.println("重新启动线程：如果已经启动过了，则这个线程就不能启动");
			thread.start();
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		testThread();
	}
	
	
}
