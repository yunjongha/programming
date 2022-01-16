package object;

import java.io.Closeable;

public class item9_TryWithResources {

	static class Printer implements Closeable {
	
		public Printer () {
		}
		
		public void print(String doc) {
			
			if(doc == null || doc.isEmpty()) {
				throw new RuntimeException("document is empty!");
			}
		}
	
		@Override
		public void close() {
			throw new RuntimeException("printer can't close!");
		}
		
	}
	
	static class Scanner implements Closeable {
		public Scanner() {
		}

		public void scan(boolean glass) {
			
			if(!glass) {
				throw new RuntimeException("it only suports glass scan!");
			}
		}
	
		@Override
		public void close() {
			throw new RuntimeException("scanner can't close!");
		}
	}
	
	public static void tryWithResources() {
		try(Printer printer = new Printer();
			Scanner scanner = new Scanner()) {
			scanner.scan(false);
			printer.print(null);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//printer.close();
			//scanner.close();
		}
	}

	public static void tryWithFinally() {

		Printer printer = new Printer();
		Scanner scanner = new Scanner();
		try {
			scanner.scan(false);
			printer.print(null);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				printer.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				scanner.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		//tryWithFinally();
		tryWithResources();
	}
}