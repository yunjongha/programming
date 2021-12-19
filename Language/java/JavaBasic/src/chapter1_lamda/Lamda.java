package chapter1_lamda;

import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.Callable;
import java.util.function.BiFunction;

/**
 * 
 * @author yunjo
 *
 */
public class Lamda {
	
	public static void main(String[] args) {
		
		String[] strings = new String[] { "jongha", "is", "good", "!" };
		
		Arrays.sort(strings, (f, s) -> Integer.compare(f.length(), s.length()) );

		BiFunction<String, String, Integer> thisFunc = (f, s) -> Integer.compare(f.length(), s.length()) ;
		
		System.out.println("strings: " + Arrays.asList(strings));
		
		Comparator<String> comp = (f, s) -> Integer.compare(f.length(), s.length());
		
		//Lamda4Exception sleeper = () -> { System.out.println("check exception!"); Thread.sleep(1000); };
		Callable<String> sleeper = () -> { System.out.println("check exception!"); Thread.sleep(1000); return null;};
	}
}
