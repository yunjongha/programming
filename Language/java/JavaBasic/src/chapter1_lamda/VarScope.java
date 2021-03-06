package chapter1_lamda;

import java.lang.Thread.State;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class VarScope {

	private static int staticInt = 10;
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
	
		VarScope vScope = new VarScope();
		vScope.repeatMessage("lamda jongha!!", 1);
		vScope.repeatByAnnon("anno jongha!!", 1);
	}
	
	/**
	 * 
	 * @param text
	 * @param count
	 */
	public void repeatMessage(String text, int count) {
		System.out.println("this in lamda: " + this.toString());
		int thisInt = 10;
		
		Runnable r = () -> {
			//count--;
			//staticInt--;
			//int thisInt = 20;
			System.out.println("lamda this: " + this.toString());
			for (int i = 0 ; i < count; i++) {
				System.out.println(text);
				System.out.println("- " + System.currentTimeMillis());
				Thread.yield();
				System.out.println("+ " + System.currentTimeMillis());
			}
		};
		
		new Thread(r).start();
	}
	
	/**
	 * 
	 * @param text
	 * @param count
	 */
	public void repeatByAnnon(String text, int count) {
		
		System.out.println("this in annon: " + this.toString());

		int thisInt = 10;
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				//count--;
				//staticInt--;
				int thisInt = 20;
				System.out.println("anonimous this: " + this.toString());
				for (int i = 0 ; i < count; i++) {
					System.out.println(text);
					System.out.println("- " + System.currentTimeMillis());
					Thread.yield();
					System.out.println("+ " + System.currentTimeMillis());
				}
			}
		}).start();
	}
	
	public static void referObj(List<Path> files) {
	
		List<Path> matches = Collections.synchronizedList(new ArrayList<>());
		for (Path p : files) {
			new Thread(() -> { 
				if (p.endsWith("jongha")) matches.add(p); 
			}).start();
		}
	}

}
