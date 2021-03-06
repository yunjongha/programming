package chapter2_stream;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class ToStream {

	public static void main(String[] args) throws IOException {
		
		closeAuto();
		
		String contents = new String(Files.readAllBytes(Paths.get("c:\\temp\\jongha.txt")), StandardCharsets.UTF_8);
		List<String> words = Arrays.asList(contents.split("[\\s|\\t]+"));
		
		/*
		int count = 0;
		for (String w : words) {
			System.out.println("word: " + w);
			if (w.length() > 12) count++;
		}
		*/
		
		long startT = System.currentTimeMillis();
		long count = words.stream().filter(w -> w.length() > 12).count();
		long endT = System.currentTimeMillis();
		
		System.out.println("count: " + count + ", elapsed T: " + (endT - startT));

		startT = System.currentTimeMillis();
		count = words.parallelStream().filter(w -> w.length() > 12).count();
		endT = System.currentTimeMillis();
		
		System.out.println("count 2: " + count + ", elapsed T 2: " + (endT - startT));
	}
	
	/**
	 * 
	 */
	public static void arrayStream() throws IOException {
		String contents = new String(Files.readAllBytes(Paths.get("c:\\temp\\jongha.txt")), StandardCharsets.UTF_8);
		Stream<String> stream = Stream.of(contents.split("[\\s|\\t]+"));
		List<String> words = Arrays.asList(contents.split("[\\s|\\t]+"));
		
		/*
		int count = 0;
		for (String w : words) {
			System.out.println("word: " + w);
			if (w.length() > 12) count++;
		}
		*/
		
		long startT = System.currentTimeMillis();
		long count = stream.filter(w -> w.length() > 12).count();
		long endT = System.currentTimeMillis();
		
		System.out.println("count: " + count + ", elapsed T: " + (endT - startT));

		startT = System.currentTimeMillis();
		count = words.parallelStream().filter(w -> w.length() > 12).count();
		endT = System.currentTimeMillis();
		
		System.out.println("count 2: " + count + ", elapsed T 2: " + (endT - startT));
	}

	/**
	 * @throws IOException 
	 * 
	 */
	public static void closeAuto() throws IOException {
		try (Stream<String> lines = Files.lines(Paths.get("c:\\temp\\jongha.txt"))){
			lines.forEach(System.out::println);
		}
	}
}
