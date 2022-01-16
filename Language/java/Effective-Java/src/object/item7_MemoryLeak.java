package object;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class item7_MemoryLeak {

	public static void main(String[] args) throws InterruptedException {
		ExecutorService service = Executors.newSingleThreadExecutor();
		service.execute(new LogManager("single"));

		Thread.sleep(1000);

		ExecutorService serviceFixed = Executors.newFixedThreadPool(1);
		serviceFixed.execute(new LogManager("fixed"));
		
		Thread.sleep(10 * 1000);
	}
}

class JobEvent {
}

class LogManager implements Runnable {

	private final BlockingQueue<JobEvent> queue = new LinkedBlockingQueue<>(1000);
	
	private volatile boolean stopped = false;
	
	@Override
	public void run() {

		while(!stopped) {
			try {
				JobEvent event = queue.take();
				// ...
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
	}
	
	public void stop() {
		stopped = true;
	}
}

class StockViewer implements StockListener {

	private final List<Company> companies = new ArrayList<>();
	
	public void forground() {
		for(Company cp : companies) {
			cp.addStockListener(this);
		}
	}
	
	public void backround() {
		for(Company cp : companies) {
			cp.removeStockListener(this);
		}
	}

	@Override
	public void update(StockEvent event) {
		//...
	}

}

class StockEvent {
	
}

class Company {

	private final List<StockListener> listeners = new ArrayList<>();
			
	public void addStockListener(StockListener listener) {
		listeners.add(listener);
	}
	
	public void removeStockListener(StockListener listener) {
		listeners.remove(listener);
	}
}

interface StockListener {
	
	public void update(StockEvent event);
}