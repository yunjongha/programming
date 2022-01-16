package method;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class item10_Equals {

	private static class WebToken {
		private final String issuer;
		private final String audience;
		private final int uniqueId;
		private final EnumSet<Scope> scopes;
		
		public WebToken(String issuer, String audience, int uniqueId, Set<Scope> scopes) {
			if (issuer == null || issuer.isEmpty()) {
				throw new IllegalArgumentException("issuer can't be empty");
			}

			if (audience == null || audience .isEmpty()) {
				throw new IllegalArgumentException("audience can't be empty");
			}

			if (scopes == null) {
				throw new IllegalArgumentException("issuer can't be null");
			}
			
			this.issuer = issuer;
			this.audience = audience;
			this.uniqueId = uniqueId;
			this.scopes =  EnumSet.copyOf(scopes);
		}
		
		@Override
		public boolean equals(Object o) {
			System.out.printf("%s equals called with %s \n", this, o);
			
			if (this == o) return true;

			if (o instanceof WebToken == false) return false;

			WebToken obj = (WebToken)o;
			if(!issuer.equals(obj.issuer)) return false;
			if(!audience.equals(obj.audience)) return false;
			if(uniqueId != obj.uniqueId) return false;
			if(!scopes.equals(obj.scopes)) return false;
			
			return true;
		}
	}
	
	private static enum Scope {
		print, scan, copy, color_print, color_copy
	}
	
	public static void main(String[] args) {
		
		//withCollections();
		//withSet();
		arrayCompare();
	}
	
	private static void withSet() {
		int uniqueId = new Random().nextInt();
		WebToken token1 = new WebToken("WeWork", "CustomerA", uniqueId, EnumSet.of(Scope.print, Scope.scan, Scope.color_copy));
		WebToken token2 = new WebToken("WeWork", "CustomerA", uniqueId, EnumSet.of(Scope.print, Scope.scan, Scope.color_copy));

		Set<WebToken> tokens = new HashSet<>();
		tokens.add(token1);
		tokens.add(token1);

		System.out.println("\n-- all data");
		
		tokens.forEach(System.out::println);

		System.out.println("\ntoken1.equals(token2): " + token1.equals(token2));

		//tokens.add(token2);
	}
	
	private static void withCollections() {
		int uniqueId = new Random().nextInt();
		
		WebToken token1 = new WebToken("WeWork", "CustomerA", uniqueId, EnumSet.of(Scope.print, Scope.scan, Scope.color_copy));
		WebToken token2 = new WebToken("WeWork", "CustomerA", uniqueId, EnumSet.of(Scope.print, Scope.scan, Scope.color_copy));
		
		List<WebToken> tokens1 = Arrays.asList(token1, token2);
		Set<WebToken> tokens2 = new HashSet<>(Arrays.asList(token1, token2));
	
		tokens1.forEach((x) -> System.out.printf("list: %s\n", x));
		tokens2.forEach((x) -> System.out.printf("set: %s\n", x));
	}
	
	private static void arrayCompare() {
		String[] str1 = {"corona", "19", "was", "over"};
		String[] str2 = {"corona", "19", "was", "over"};
		
		System.out.println("array equals : " + str1.equals(str2));
		
		List<String> list1 = Arrays.asList(str1);
		List<String> list2 = Arrays.asList(str2);
		
		ArrayList<String>

		System.out.println("list equals : " + list1.equals(list2));
	}
}
