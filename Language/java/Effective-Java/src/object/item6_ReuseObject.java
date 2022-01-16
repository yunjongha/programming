package object;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class item6_ReuseObject {

	public static void main(String[] args) throws Exception {
		//ageByList();
		ageByArray();
	}
	
	private static void ageByList() {
		Random ran = new Random();
		List<Integer> peopleList = new ArrayList<>();
		for(int i = 0 ; i < 50_000_000; i++) {
			int nextRan = ran.nextInt(150);
			peopleList.add(nextRan);
		}
		
		divideAge(peopleList);
	}

	public static void ageByArray() {
		
		Random ran = new Random();
		int[] peopleArray = new int[50_000_000];
		for(int i = 0 ; i < 50_000_000; i++) {
			int nextRan = ran.nextInt(150);
			peopleArray[i] = nextRan;
		}
		
		divideAge(peopleArray);
	}


	private void valueOfs() {
		Integer.valueOf(1);
		Boolean.valueOf(true);
		Long.valueOf(1);
		Float.valueOf(0.1f);
		Double.valueOf(0.2);
		String.valueOf(1);
	}
	
	private static void stringLiteral() {
		String strLiteral = "string literal"; // the string is stored in the spring pool area where the same string is string literald
		String strNew = new String("string literal"); //the string is stored in the heap memory
		
		System.out.printf("strLiteral == \"string literal\" : %s \n", strLiteral == "string literal");
		System.out.printf("strLiteral == strNew : %s \n", strLiteral == strNew );

		System.out.printf("strLiteral.equals(strNew)  : %s \n", compareStr(strLiteral, strNew));
	}
	
	private static boolean compareStr(String str1, String str2) {
		if (str1 == null) {
			return str1 == str2;
		}

		return str1.equals(str2);
	}

	private static void divideAge(List<Integer> people) {

		System.out.println("--- start divide list");
		
		long start = System.currentTimeMillis();
		
		List<Integer> young = new ArrayList<>();
		List<Integer> middle = new ArrayList<>();
		List<Integer> old = new ArrayList<>();

		for(Integer person : people) {
			if (person < 30) {
				young.add(person);
			} else if(person < 60) {
				middle.add(person);
			} else {
				old.add(person);
			}
		}
		
		long end = System.currentTimeMillis();
		
		System.out.printf("list duration: %s \n",  (end - start));

		System.out.println("--- end divide list");
	}

	private static void divideAge(int[] people) {
		
		System.out.println("--- start divide array");
		long start = System.currentTimeMillis();

		int[] young = new int[people.length];
		int[] middle = new int[people.length];
		int[] old = new int[people.length];
		
		int youngNum = 0;
		int middleNum = 0;
		int oldNum = 0;
		for(int person : people) {
			if(person < 30) {
				young[youngNum++] = person;
			} else if(person < 60) {
				middle[middleNum++] = person;
			} else {
				old[oldNum++] = person;
			}
		}

		long end = System.currentTimeMillis();

		System.out.printf("array duration: %s \n",  (end - start));

		System.out.println("--- end divide array");
	}
}
