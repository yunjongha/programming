package chapter2_stream;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectorStream {

	private static final String[] words = new String[] {"chanbin", "dohyeon", "jongha", "chamnu", 
			"joo", "jis", "good", "jood", "good"}; 
	
	public static void main(String[] args) {
		//toList();
		iterate();
	}
	
	public static void toList() {
		List<String> list = Stream.of(words).collect(Collectors.toList());
		
		list.forEach(System.out::println);
		
		for(String anItem : list) {
			System.out.println("list item: " + anItem);
		}
	}
	
	public static void iterate() {
		List<String> list = Stream.of(words).collect(Collectors.toList());
		
		for(String anItem : list) {
			System.out.println("stream item: " + anItem);
		}
	}
}


