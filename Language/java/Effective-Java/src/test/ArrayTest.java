package test;

import java.util.Arrays;

public class ArrayTest {

	public static void main(String[] args) {

		String[] strs1 = new String[] { "jongha", "is", "good" };
		String[] strs2 = new String[] { "jongha", "is", "good" };
		
		System.out.printf("equals: %s \n", strs1.equals(strs2));
		System.out.printf("== : %s \n", strs1 == strs2 );

		compareStringArray(strs1, strs1);
		
		System.out.printf("updated: %s \n", Arrays.asList(strs1));
	}
	
	
	private static void compareStringArray(String[] array1, String[] array2) {
		System.out.printf("== in function : %s \n", array1 == array2);
		
		array1[0] = "yun jongha";
		
	}
}


