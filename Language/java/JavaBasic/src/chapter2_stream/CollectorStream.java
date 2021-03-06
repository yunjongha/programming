package chapter2_stream;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectorStream {

	private static final String[] words = new String[] {"chanbin", "dohyeon", "jongha", "chamnu", 
			"joo", "jis", "good", "jood", "good"}; 
	
	public static void main(String[] args) {
//		toList();
//		System.out.println("-----");
//		toSet();
//		joining();
		summarize();
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
	
	public static void joining() {
		String result = Stream.of(words).collect(Collectors.joining(", "));
		
		System.out.println("result: " + result);
		
		Optional<String> result2 = Stream.of(words).reduce((x, y) -> x + ", " + y);

		System.out.println("result2: " + result2.get());
	}
	
	public static void summarize() {
		IntSummaryStatistics summary = Stream.of(words).collect(Collectors.summarizingInt(String::length));
		
		System.out.println(summary.getAverage());
		System.out.println(summary.getCount());
		System.out.println(summary.getMax());
		System.out.println(summary.getMin());
		System.out.println(summary.getSum());

		System.out.println(summary);
	}
}


