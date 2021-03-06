package chapter2_stream;

import java.util.stream.Stream;

public class FilterMap {

	public static void main(String[] args) {
		execIter();
	}
	
	public static void execIter() {
		Object[] powers = Stream.iterate(1.0, p -> p * 2)
								.peek(e -> System.out.println("fetching: " + e))
								.limit(20).toArray();
		
		Stream.of(powers).forEach(System.out::println);
	}

	public static void execLimit() {
		Stream<Double> randoms = Stream.generate(Math::random).limit(100);
		randoms.forEach(System.out::println);
	}

	public static void execMap() {
		Stream<String> lowCaseWords = Stream.of("THIS", "IS", "LIFE").map(String::toLowerCase);
		
		lowCaseWords.forEach(System.out::println);

		Stream<String> firstChars = Stream.of("THIS", "IS", "LIFE").map( x -> x.substring(0, 1)).map(String::toLowerCase);

		firstChars.forEach(System.out::println);
	}
}
