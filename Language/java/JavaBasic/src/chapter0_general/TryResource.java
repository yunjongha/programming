package chapter0_general;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class TryResource {
	
	private static final int BUFFER_SIZE = 256;

	public static void main(String[] args) {
		
	}
	
	public static void copy(String src, String des) throws IOException {
		try (InputStream in = new FileInputStream(src);
			OutputStream out = new FileOutputStream(des)) {
			
			byte[] buf = new byte[BUFFER_SIZE];
			int n;
			while ((n = in.read(buf)) >= 0) {
				out.write(buf, 0, n);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			System.out.println("finally can be called without closing streams!");
		}
	}
}
