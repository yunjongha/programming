package chapter0_general;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class NoNullReturn {

	private static final List<Cheese> cheeses = Arrays.asList( new Cheese[] { new Cheese("ch1"), new Cheese("ch2") } );

	public static void main(String[] args) {
		Cheese[] chs = toCheese();

		Stream.of(chs).forEach(System.out::println);
	}
	
	public static List<Cheese> getCheese() {
		return cheeses.isEmpty() ? Collections.emptyList() : new ArrayList<>(cheeses);
	}
	
	public static Cheese[] toCheese() {
		//return (Cheese[])cheeses.toArray();
		return cheeses.toArray(new Cheese[0]);
	}
};

final class Cheese {

	private final String name;
	
	public Cheese(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name;
	}
};
