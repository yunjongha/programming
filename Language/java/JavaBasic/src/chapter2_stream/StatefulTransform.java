package chapter2_stream;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class StatefulTransform {

	private static final String[] words = new String[] {"chanbin", "dohyeon", "jongha", "chamnu", 
			"joo", "jis", "good", "jood", "good"}; 
	
	public static void main(String[] args) {
		//distinct();
		//sort();
		//max();
		//find();
		stringT();
	}
	
	public static void distinct() {
		Stream<String> uniqueWords = Stream.of(words).distinct();
		uniqueWords.forEach(System.out::println);
	}
	
	public static void sort() {
		Stream<String> shortest = Stream.of(words).sorted(Comparator.comparing(String::length));
		shortest.forEach(System.out::println);

		Stream<String> longest = Stream.of(words).sorted(Comparator.comparing(String::length).reversed());
		longest.forEach(System.out::println);
	}
	
	public static void max() {
		Optional<String> largest = Stream.of(words).max(String::compareToIgnoreCase);
		Optional<String> largest1 = Stream.of(words).max((x,y) -> x.compareToIgnoreCase(y));
		if (largest.isPresent()) {
			System.out.println("largets: " + largest.get());
		}
	}
	
	public static void find() {
		Optional<String> startsJ = Stream.of(words).parallel().filter(x -> x.startsWith("j")).findFirst();
		if(startsJ.isPresent()) {
			System.out.println("find first: " + startsJ.get());
			System.out.println("find : " + startsJ);
		}

		Optional<String> startsPJ = Stream.of(words).parallel().filter(x -> x.startsWith("j")).findAny();
		
		startsPJ.orElseGet(() -> System.getProperty("user.dir"));
		
		List<String> results = new ArrayList<>();
		startsPJ.ifPresent(System.out::println);
		startsPJ.ifPresent(results::add);
		
		if(startsPJ.isPresent()) {
			System.out.println("find any: " + startsPJ.get());
			System.out.println("find : " + startsPJ);
		}
		
		StringT aT = new StringT("tel");
		boolean matched2 = Stream.of(words).parallel().anyMatch(x -> x.startsWith("j"));

		if(startsPJ.isPresent()) {
			System.out.println("find any: " + startsPJ.get());
			System.out.println("find : " + startsPJ);
		}
	}
	
	public static void stringT() {
		StringT[] aTArray = new StringT[] { 
				new StringT("first"), new StringT("second"), new StringT("third"),
				new StringT("2first"), new StringT("2second"), new StringT("2third"),
				new StringT("3first"), new StringT("3second"), new StringT("3hird")
		};

		boolean matched = Stream.of(aTArray).parallel().anyMatch(StringT::startsWithA);
		
		System.out.println("matched: " + matched);

	}
}

final class StringT {
	
	private final String data;
	
	public StringT(String data) {
		this.data = data;
	}

	public boolean startsWith(String param) {
		return true;
	}
	
	public boolean startsWithA() {
		System.out.println("[" + Thread.currentThread().getName() + "] " + data);
		
		if(data.equals("thord")) {
			return true;
		}
		
		return false;
	}
	
	
}
