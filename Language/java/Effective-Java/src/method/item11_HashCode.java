package method;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class item11_HashCode {

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
		
		public int hashCode() {
			System.out.printf("WebToken hash called! \n");
			//return 0;
			return super.hashCode();
		}
		
		@Override
		public boolean equals(Object o) {
			System.out.printf("WebToken equals called! \n");
			
			if (this == o) return true;

			if (o instanceof WebToken == false) return false;

			WebToken obj = (WebToken)o;
			if(!issuer.equals(obj.issuer)) return false;
			if(!audience.equals(obj.audience)) return false;
			if(uniqueId != obj.uniqueId) return false;
			if(!scopes.equals(obj.scopes)) return false;
			
			return true;
		}
		
		@Override
		public String toString() {
			return "WebToken@" + super.hashCode();
		}
	}
	
	private static enum Scope {
		print, scan, copy, color_print, color_copy
	}

	private static class User implements Comparable<User>{
		private final int accountNum;
		private final String name;
		private volatile int hashCode = 0;
		
		private boolean logicHash = false;
		private boolean logicEqual = false;
		
		
		public User(int accountNum, String name) {
			this.accountNum = accountNum;
			this.name = name;
		}
		
		public void setLogic(boolean logicHash, boolean logicEqual) {
			this.logicHash = logicHash;
			this.logicEqual = logicEqual;
		}
		
		@Override
		public int hashCode() {
			if(logicHash) {
				return logicHash();
			}

			return super.hashCode();
		}
		
		private int logicHash() {
			int result = hashCode;
		
			if ( result == 0) {
				result = Integer.hashCode(accountNum);
				result = 31 * result + Objects.hashCode(name);
				hashCode = result;
			}
			
			return result;
		}
		
		@Override
		public boolean equals(Object o) {
			if (logicEqual) {
				return logicEqual(o);
			}

			return super.equals(o);
		}
		
		private boolean logicEqual(Object o) {

			if (this == o) return true;
			if ( o instanceof User == false) return false;
			
			User obj = (User)o;
			if (this.accountNum != obj.accountNum) return false;
			if (!Objects.equals(this.name, obj.name)) return false;
			
			return true;
		}
		
		@Override
		public String toString() {
			return "User@" + super.hashCode();
		}

		@Override
		public int compareTo(User o) {
			if (this.equals(o)) return 0;
			
			if (this.accountNum > o.accountNum) return 1;
			
			return -1;
		}
	}

	private static void withMap(boolean logicHash, boolean logicEqual) {
		int uniqueId = new Random().nextInt();
		WebToken token = new WebToken("WeWork", "CustomerA", uniqueId, EnumSet.of(Scope.print, Scope.scan, Scope.color_copy));

		User user1 = new User(1111, "user");
		user1.setLogic(logicHash, logicEqual);

		User user2 = new User(1111, "user");
		user2.setLogic(logicHash, logicEqual);

		Map<User, WebToken> maps = new HashMap<>();
		
		maps.put(user1, token);
		maps.put(user2, token);
		
		System.out.printf("[logicHash:%s, logicEqual:%s]\n", logicHash, logicEqual);
		maps.entrySet().forEach(System.out::println);
		System.out.println();
	}
	
	private static void withSet(boolean logicHash, boolean logicEqual) {
		User user1 = new User(1111, "user");
		user1.setLogic(logicHash, logicEqual);

		User user2 = new User(1111, "user");
		user2.setLogic(logicHash, logicEqual);

		Set<User> set = new HashSet<>();
		set.add(user1);
		set.add(user2);

		System.out.printf("[logicHash:%s, logicEqual:%s]\n", logicHash, logicEqual);
		set.forEach(System.out::println);
		System.out.println();

	}
	
	private static void withTreeSet(boolean logicHash, boolean logicEqual) {
		User user1 = new User(1111, "user");
		user1.setLogic(logicHash, logicEqual);

		User user2 = new User(1111, "user");
		user2.setLogic(logicHash, logicEqual);

		Set<User> set = new TreeSet<>();
		set.add(user1);
		set.add(user2);

		System.out.printf("[logicHash:%s, logicEqual:%s]\n", logicHash, logicEqual);
		set.forEach(System.out::println);
		System.out.println();

	}
	
	public static void main(String[] args) {
		withMap(false, false);
		withMap(true, false);
		withMap(false, true);
		withMap(true, true);

		System.out.println(" -- set -- ");

		withSet(false, false);
		withSet(true, false);
		withSet(false, true);
		withSet(true, true);
		
		System.out.println(" -- tree set --");

		withTreeSet(false, false);
		withTreeSet(true, false);
		withTreeSet(false, true);
		withTreeSet(true, true);
	}
}
