package test;

public class OperatorTest {

	public static void main(String[] args) {
		//leftShift();
		andMode();
	}
	
	private static void andMode() {
		
		for(int i = 1 ; i < Integer.MAX_VALUE; i++) {
			boolean diff = (i & 15) == (i % 16);
			if(!diff) {
				System.out.printf("and != mode : %s \n", i); 
			}
		}
		
	}

	private static void leftShift() {

		for(int i = 1 ; i < 5 ; i++) {
			System.out.printf("%s << : %s \n", i, 1 << i);
		}
		
	}
}
