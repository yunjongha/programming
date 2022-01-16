package chapter0_general;

import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

import org.junit.jupiter.api.Test;

public class Singleton {

	private static Object loadClass(ClassLoader classLoader) throws Exception {

		System.out.println("---------------------------");
		System.out.println(" current classloader: " + classLoader);

		ClassLoader ploader = classLoader;
		do {
			ClassLoader cloader = ploader;
			ploader = cloader.getParent();

			System.out.println(" parent classloader: " + ploader);
		} while (ploader != null);

		Class<?> loadedClass = (Class<?>)classLoader.loadClass("test.Singleton");
		Method method = loadedClass.getMethod("getInstance");
		Object newInstance =  method.invoke(loadedClass);

		System.out.printf(" instance: %s, class loader: %s \n", newInstance, newInstance.getClass().getClassLoader());

		return newInstance;
	}

	public static void main(String[] args) throws Exception {

		
		URL[] url = new URL[] { new URL("file:\\C:\\Temp\\singleton.jar") };

		URLClassLoader classLoader1 = new URLClassLoader(url);
		Object instance1 = loadClass(classLoader1);
		
		URLClassLoader classLoader2 = new URLClassLoader(url);

		Object instance2 = loadClass(classLoader2);
		System.out.printf("equals (loader1, loader2): %s \n", instance1.equals(instance2));
	
		Object instance3 = loadClass(classLoader1);
		System.out.printf("equals (loader1, loader3): %s \n", instance1.equals(instance3));

		Object instance4 = loadClass(classLoader1.getParent());
		System.out.printf("equals (loader1, loader4): %s \n", instance1.equals(instance4));
	}

}