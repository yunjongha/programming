package chapter1_lamda;

import java.nio.file.FileSystems;
import java.nio.file.Path;

public class StaticInterface {

	public static void main(String[] args) {
		
	}
}

interface StaticPerson {
	
	//public int getAge();
	
	public static Path get(String first, String... more) {
		return FileSystems.getDefault().getPath(first, more);
	}
}