package chapter1_lamda;

import java.awt.Button;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * 
 * @author yunjo
 *
 */
public class ConstructorRef {

	public static void main(String[] args) {
	
		List<String> labels = Arrays.asList(new String[] { "jongha", "is", "good", "!" });
		Stream<Button> stream = labels.stream().map(Button::new);
		List<Button> buttons = stream.collect(Collectors.toList());
		
		System.out.println("buttions: " + buttons);
		
		stream.toArray();
		Button[] buttions1 = stream.toArray(Button[]::new);
	}
}
