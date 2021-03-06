package chapter0_general;

import java.util.ArrayList;
import java.util.List;

public class GenericWildcard {

	public static void main(String[] args) {
		List<? super A> listStr = new ArrayList<>();
		listStr.add(new A("A"));
		listStr.add(new B("B"));
		listStr.add(new C("C"));
	
		//A a = listStr.get(0);

		List<A> listA = new ArrayList<>();
		listStr = listA;
		
		listStr.stream().forEach(System.out::println);
		
		List<? extends A> listStr2 = new ArrayList<>();
		A a2 = listStr2.get(0);
//		listStr2.add(new A("A"));
//		listStr2.add(new B("B"));
//		listStr2.add(new C("C"));

		listStr2.stream().forEach(System.out::println);
	}
}

class A {

	protected final String data;
	
	public A(String data) {
		this.data = data;
	}
	
	@Override
	public String toString() {
		return data;
	}
}

class B extends A {

	public B(String data) {
		super(data);
	}
}

class C extends B {
	
	public C(String data) {
		super(data);
	}
}

