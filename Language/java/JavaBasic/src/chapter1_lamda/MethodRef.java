package chapter1_lamda;

import java.util.Arrays;

/**
 * 
 * @author yunjo
 *
 */
public class MethodRef {

	public static void main(String[] args) {
		String[] strings = new String[] { "jongha", "is", "good", "!" };
		
		Arrays.sort(strings, String::compareToIgnoreCase);
		
		System.out.println("strings: " + Arrays.asList(strings));
	}
}

/**
 * 
 * @author yunjo
 *
 */
class Greeter {

	public void greet() {
		System.out.println("Hello, world!");
	}
}

/**
 * 
 * @author yunjo
 *
 */
class ConcurrentGreeter extends Greeter {
	public void greet() {
		Thread t = new Thread(super::greet);
		t.start();
	}
}