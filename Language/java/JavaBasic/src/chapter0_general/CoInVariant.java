package chapter0_general;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class CoInVariant {

	
	public static void main(String[] args) {
		
		//covariant();
		covariant2();
	}
	
	public static void covariant() {
		String[] strings = new String[] { "jongha", "is", "good" };
		
		Object[] objs = strings;
		
		Stream.of(objs).forEach(System.out::println);
		
		System.out.println("--------");
		
		objs[0] = 0;
		objs[1] = 1;
		objs[2] = 2;

		Stream.of(objs).forEach(System.out::println);
	}
	
	public static void covariant2() {
		Object[] objs = new Object[3];
		
		objs[0] = "string";
		objs[1] = 20;
		objs[2] = 30.0;
		
		Stream.of(objs).forEach(System.out::println);
	}
	
	public static void invariant() {
		
		List<String> strList = Arrays.asList("jongha", "is", "very", "good");
		
		//List<Object> objList = strList;

		//objList.forEach(System.out::println);
		
	}
}
