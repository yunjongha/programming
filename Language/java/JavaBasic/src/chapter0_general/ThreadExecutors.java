package chapter0_general;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadExecutors {

	private static AtomicInteger count = new AtomicInteger(0);
	
	public static void main(String[] args) {
		//startExecutor();
		startThread();
	}
	
	public static void startExecutor() {
		ExecutorService service = Executors.newSingleThreadExecutor();
		
		for(int i = 0 ; i < 5 ; i++) {
			service.execute(() -> { 
				System.out.println(Thread.currentThread().getName() + ", execute " + count.incrementAndGet());
			});
			
			if (i == 3) {
				service.shutdown();
			}
		}
	}
	
	public static void startThread() {
		new Thread(() -> System.out.println("start thread!")).start();
	}
}
