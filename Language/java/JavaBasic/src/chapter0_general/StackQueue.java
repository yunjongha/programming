package chapter0_general;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.stream.Stream;

public class StackQueue {

	public static void main(String[] args) {
		Deque<Integer> dequeue = new ArrayDeque<>();
	
		int[] nums = new int[] { 4, 5, 6, 7, 8, 9};
		
		//int[] nums = null;
		
		for(Integer anItem : nums) {
			dequeue.addLast(anItem);
			System.out.println("added: " + anItem);
		}
		
		for(int i = 0 ; i < 3 ; i++) {
			int removed = dequeue.removeLast();
			dequeue.addFirst(removed);
		}
		
		System.out.println("size : " + dequeue.size());

		int[] result = new int[dequeue.size()];
		
		int size = dequeue.size();
		for(int i = 0 ; i < size ; i++) {
			result[i] = dequeue.removeFirst();
			System.out.println("result: " + result[i]);
		}

		
	}
}
