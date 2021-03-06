package chapter0_general;

import java.util.Objects;

public class HashcodeDefinition {

	private int age;
	private short height;
	private String name;
	
	private int hashCode;
	
	public HashcodeDefinition(int age, short height, String name) {
		this.age = age;
		this.height = height;
		this.name = name;
	}

	public static void main(String[] args) {
		HashcodeDefinition def1 = new HashcodeDefinition(31, (short)20, "jongha");
		HashcodeDefinition def2 = new HashcodeDefinition(31, (short)20, "jongha");
		
		System.out.println("== " + (def1 == def2));
		System.out.println("equals " + def1.equals(def2));
		System.out.println("hash " + def1.hashCode() + ", " + (def1.hashCode() == def2.hashCode()));
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		if(!(obj instanceof HashcodeDefinition)) return false;
		
		HashcodeDefinition objDef = (HashcodeDefinition)obj;
		return age == objDef.age && height == objDef.height && Objects.equals(name, objDef.name);
	}

	@Override
	public int hashCode() {

		if (hashCode == 0) {
			int result = Integer.hashCode(age);
			result = 31 * result + Short.hashCode(height);
			result = 31 * result + Objects.hashCode(name);
			
			hashCode = result;
		}
		
		return hashCode;
	}
	
}
