package chapter0_general;

import java.util.Objects;

public class EqualsDefinition {
	
	private String name;
	private int age;
	private double sleepTime;
	private float eatTime;

	public EqualsDefinition(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public static void main(String[] args) {
		String data = "jongha";
		String data1 = "jongha";
		
		System.out.println("equal? : " + (data == data1));
		System.out.println("equal? : " + (data.equals(data1)));
		
		EqualsDefinition def1 = new EqualsDefinition("jongha", 30);
		EqualsDefinition def2 = new EqualsDefinition("jongha", 30);
		
		System.out.println("== " + (def1 == def2));
		System.out.println("equals " + (def1.equals(def2)));
		System.out.println("null " + (def1.equals(null)));
		System.out.println("Objects " + Objects.equals(data, data1));
		System.out.println("Objects null " + Objects.equals(null, null));
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		
		if(!(o instanceof EqualsDefinition)) {
			return false;
		}
		
		EqualsDefinition paramObj = (EqualsDefinition)o;
		return Objects.equals(name, paramObj.name) 
				&& this.age == paramObj.age
				&& Double.compare(sleepTime, paramObj.sleepTime) == 0
				&& Float.compare(eatTime, paramObj.eatTime) == 0;
	}
}
