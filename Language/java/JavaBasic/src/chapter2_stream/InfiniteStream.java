package chapter2_stream;

import java.util.stream.Stream;

public class InfiniteStream {

	public static void main(String[] args) {

		Stream<Double> echos = Stream.generate(Math::random);

		echos.forEach(System.out::println);
	}
}
