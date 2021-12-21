package turing;

import java.util.Iterator;
import java.util.Optional;
import java.util.Scanner;
import java.util.Stack;

public class Baseball {

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		
		//String[] ops = in.nextLine().split(" ");
		
		String[] ops = new String[] { "5", "2", "C", "D", "+" };
		
		Stack<Integer> stack = new Stack<>();

		for(String item : ops) {
			if (item != null && !item.isEmpty()) {
				if(item.equals("C")) {
					try {
						stack.pop();
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else if (item.equals("D")) {
					try {
						Integer prevScore = stack.pop();
						stack.push(prevScore);
						stack.push(prevScore * 2);
					} catch (Exception e) {
						e.printStackTrace();
					}

				} else if (item.equals("+")) {
					try {
						Integer prevScore1 = stack.pop();
						Integer prevScore2 = stack.pop();
						stack.push(prevScore2);
						stack.push(prevScore1);
						stack.push(prevScore1 + prevScore2);
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					try {
						stack.push(Integer.parseInt(item));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		
		int result = 0;
		for(Iterator<Integer> i = stack.iterator() ; i.hasNext() ; ) {
			result += i.next();
		}
		
		Optional<Integer> reduced = stack.stream().reduce((x, y) -> x + y);
	
		System.out.printf("result: %s, reduced: %s", result, reduced.get());
		
	}
}
