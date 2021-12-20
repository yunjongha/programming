package chapter2_stream;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectorStream {

	private static final String[] words = new String[] {"chanbin", "dohyeon", "jongha", "chamnu", 
			"joo", "jis", "good", "jood", "good"}; 
	
	public static void main(String[] args) {
		toList();
		System.out.println("-----");
		toSet();
	}
	
	public static void toList() {
		List<String> list = Stream.of(words).collect(Collectors.toList());
		
		list.forEach(System.out::println);
	}
	
	public static void toSet() {
		Set<String> set = Stream.of(words).collect(Collectors.toSet());
		
		set.forEach(System.out::println);
	}

	public static void toTreeSet() {
		Set<String> set = Stream.of(words).collect(Collectors.toCollection(TreeSet::new));
		
		set.forEach(System.out::println);
	}
}


