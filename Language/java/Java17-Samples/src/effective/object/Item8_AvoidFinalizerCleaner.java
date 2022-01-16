package effective.object;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.ref.Cleaner;
import java.lang.ref.Cleaner.Cleanable;
import java.util.Random;
import java.util.Stack;

public class Item8_AvoidFinalizerCleaner {

	public static void main(String[] args) throws Exception {
				
		createLoggers();

		Thread.sleep(1000);
	}
	
	private static void createLoggers() throws Exception{

		System.out.println("--- start create loggers");

		Stack<JobLogger> loggers = new Stack<>();
		
		for (int i = 0 ; i < 100 ; i++) {
			String loggerName = String.format("c:\\temp\\JavaTest\\job.log%s", i);

			loggers.add(new JobLogger(loggerName));
			loggers.pop();

			System.out.printf("--- logger [%s] is created\n", loggerName);
		}
		
		System.out.println("--- end create loggers");

	}
}

class JobLogger {

	private final static Cleaner cleaner = Cleaner.create();
	private final Cleanable cleanable;
	private final State state;

	private final FileWriter writer;
	private final String fileName;
	
	private final Stack<Integer> dummy = new Stack<>();
	
	/** Cleaner Inner Class */
	private class State implements Runnable {

		private final String fileName;
		private volatile FileWriter writer;

		public State(String fileName, FileWriter writer) {
			this.fileName = fileName;
			this.writer = writer;
		}

		@Override
		public void run() {
			System.out.printf("in clean, % is closed! \n", fileName);
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

	public JobLogger(String fileName) throws Exception {

		this.fileName = fileName;
		this.writer = new FileWriter(fileName);

		state = new State(fileName, writer);
		cleanable = cleaner.register(this, state);

		Random ran = new Random();
		for (int i = 0 ; i < 10_000_000; i++) {
			dummy.add(ran.nextInt());
		}
	}
	
	public void close() {
		cleanable.clean();
	}
	
	@Override
	protected void finalize() {
		try {
			writer.close();
			System.out.printf("in finalize, % is closed! \n", fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}