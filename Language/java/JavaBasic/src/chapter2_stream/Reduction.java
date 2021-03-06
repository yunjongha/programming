package chapter2_stream;

import java.util.Optional;
import java.util.stream.Stream;

public class Reduction {

	private static final String[] words = new String[] {"chanbin", "dohyeon", "jongha", "chamnu", 
			"joo", "jis", "good", "jood", "good"}; 

	private static final Integer[] nums = new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 1 };

	public static void main(String[] args) {
		reduceNum();
		reduceStr();
	}
	
	public static void reduceNum() {
	
		Optional<Integer> resultInt = Stream.of(nums).parallel().reduce(
				(x,y) -> {
					System.out.println("x:" + x + ", y:" + y ); return x + y;
				});
		
		resultInt.ifPresent(System.out::println);
	}

	public static void reduceStr() {
	
		Optional<String> resultInt = Stream.of(words).parallel().reduce(
				(x,y) -> {
					System.out.println("x:" + x + ", y:" + y ); return x + y;
				});
		
		if(resultInt.isPresent()) {
			System.out.println("len: " + resultInt.get().length());
		}

		resultInt.ifPresent(System.out::println);

	}

}
