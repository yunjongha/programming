package chapter2_stream;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectorMap {
	
	private static Person[] persons = new Person[] { new Person(10, "Bin"), new Person(15, "Mu"), new Person(40, "Ha"), new Person(41, "Hyeon") };
	
	public static void main(String[] args) {
		toMap();
	}
	

	public static void toMap() {
		Map<Integer, String> map = Stream.of(persons).collect(Collectors.toMap(Person::getId, Person::getName));
		
		map.forEach((x, y) -> System.out.printf("key: %s, value: %s\n", x, y));

		Map<Integer, Person> map2 = Stream.of(persons).collect(Collectors.toMap(Person::getId, Function.identity()));

		map2.forEach((x, y) -> System.out.printf("key: %s, value: %s\n", x, y));
	}
}

class Person {

	private int id;
	private String name;
	
	public Person(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}
	
	
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return String.format("%s, %s", id, name);
	}
}
