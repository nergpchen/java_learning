package example.proxy.annotation;

@MyAnnotaion
public class MyTest {

	@MyAnnotaion
	public void init(){
		
	}
	
	public static void main(String[] args) {
		MyAnnoationProcess.processAnnotations(new MyTest());
		
	}
}
