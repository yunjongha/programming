package test;

import java.util.Random;

public class RandomTest {
	
	public static void main(String[] args) {
		Random ran = new Random();
		for(int i = 0 ; i < 20 ; i++) {
			System.out.printf("next ran: %s, \n", ran.nextInt(20));
		}
	}

}
