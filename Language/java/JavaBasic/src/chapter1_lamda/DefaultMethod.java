package chapter1_lamda;

import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author yunjo
 *
 */
public class DefaultMethod {

	public static void main(String[] args) {
		List<String> list = Arrays.asList("jongha", "is", "good", "!");
		
		
		for(int i = 0 ; i < list.size() ; i++) {
			System.out.println(list.get(i));
		}
		
		for (String item : list) {
			System.out.println(item);
		}

		System.out.println(("-----"));
		list.forEach(System.out::println);
		
		Person aPer = () -> 10;
		
		System.out.println("id: " + aPer.getId());
		System.out.println("name: " + aPer.getName());
	}
}

class ThePerson implements Person, NamedPerson {

	@Override
	public String getName() {
		//return NamedPerson.super.getName();
		
		return "ther person is jongha";
	}

	@Override
	public long getId() {
		return 10;
	}
	
}

interface Person {
	long getId();
	
	default String getName() { return "Jongha Yun"; }
	//String getName();
}

interface NamedPerson {
	default String getName() { return "Named Jongha Yun"; }
	
}