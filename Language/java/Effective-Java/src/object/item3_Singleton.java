package object;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class item3_Singleton {

	public static void main(String[] args) {

		Printer printer = Printer.getInstance();
		printer.print(() -> DocType.PDF);

		Print.Printer.print(() -> DocType.Raw);;
		
		Printer printer1 = new Printer();
	}
}

class Printer {

	//private static final Printer INSTANCE = new Printer();
	private static Printer instance;
	private static final Lock instanceLock = new ReentrantLock();
	
	private int totalTray;
	private int activeTray;
	private boolean active;
	
	protected Printer() {
		instanceLock.lock();
		try {
			if (Printer.instance != null) {
				throw new IllegalStateException("Multiple instances can't be allowed");
			}
		} finally {
			instanceLock.unlock();
		}
	}

	public void print(Document doc) {
		//print raw type
	}
	
	public static Printer getInstance() {

		instanceLock.lock();
		try {
			if (instance != null) {
				return instance;
			}

			switch(Config.PRINTER_TYPE) {
			case Printer: instance = new Printer(); break;
			case Copier_2IN:  instance = new Copier2In(); break;
			case Copier_3IN:  instance = new Copier3In(); break;
			}
			
			return instance;
		} finally {
			instanceLock.unlock();
		}
	}
}

enum Print {
	Printer;
	
	private int totalTray;
	private int activeTray;
	private boolean active;

//	private Print() {
//	}

	public void print(Document doc) {
		
	}
}


class Config {
	public static final PrinterType PRINTER_TYPE = PrinterType.Copier_3IN;
}

enum PrinterType {
	Printer, Copier_2IN, Copier_3IN
}

class Scanner {
	
}

class Copier2In extends Printer {
	
	
	@Override
	public void print(Document doc) {
		
		switch(doc.getType()) {
		case Raw: 
			super.print(doc); 
			break;
		case ScanImage:
			//print scanned image
			break;
		case PDF:
			//print pdf
			break;
		}
	}
	
	public void scan() {
		//do scan
	}
}

class Copier3In extends Copier2In {

	public void fax() {
		
	}
}

enum DocType {
	Raw, ScanImage, PDF
}

interface Document {
	
	public DocType getType(); 
}
