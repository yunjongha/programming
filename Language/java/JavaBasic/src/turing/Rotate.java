package turing;

import java.util.ArrayDeque;
import java.util.Deque;

public class Rotate {

	public static void main(String[] args) {
		int[] nums = new int[] { 4, 5, 6, 7, 8, 9};
		int k = 5;
		
		int[] deque = useDeque(nums, k);
		int[] raw = useDeque(nums, k);
		
		boolean same = true;
		if(deque.length == raw.length) {
			for(int i = 0 ; i < deque.length ; i++) {
				if(deque[i] != raw[i]) {
					System.out.printf("%s is differnt, deque:%s, raw:%s \n", i, deque[i], raw[i]);
					same = false;
					break;
				} else {
					System.out.printf("%s item: %s\n", i, deque[i]);
				}
			}
		}
		
		System.out.println("same: " + same);
	}
	
	public static int[] useDeque(int[] nums, int k) {
		Deque<Integer> dequeue = new ArrayDeque<>();
	
		for(Integer anItem : nums) {
			dequeue.addLast(anItem);
			//System.out.println("added: " + anItem);
		}
		
		for(int i = 0 ; i < k ; i++) {
			int removed = dequeue.removeLast();
			dequeue.addFirst(removed);
		}
		
		//System.out.println("size : " + dequeue.size());

		int[] result = new int[dequeue.size()];
		
		int size = dequeue.size();
		for(int i = 0 ; i < size ; i++) {
			result[i] = dequeue.removeFirst();
			//System.out.println("result: " + result[i]);
		}
		
		return result;
	}
	
	public static int[] useRaw(int[] nums, int k) {
		
		int[] results = new int[nums.length];
	
		int mov = nums.length - k;
		for(int i = 0 ; i < mov ; i++) {
			results[k + i] = nums[i];
		}
		
		for(int i = 0 ; i < k ; i++) {
			results[i] = nums[k + i];
		}
		
		return results;
	}
}
