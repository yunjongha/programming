package object;

import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;

public class item5_DependencyInjector {

}

class LoginManager {
	
	private volatile IAuthenticator authenticator;
	private volatile IAuthorizer authorizer;
	
	public void setAuthenticator(IAuthenticator authenticator) {
		this.authenticator = authenticator;
	}

	public void setAuthorizer(IAuthorizer authorizer) {
		this.authorizer = authorizer;
	}
	
	
	public List<Authority> login(String id, String pwd)  {
		if(authenticator == null || authorizer == null) {
			return Collections.emptyList();
		}

		if(authenticator.authenticate(id, pwd)) {
			return authorizer.authorize(id);
		}
		
		return Collections.emptyList();
	}
}


class Authenticator implements IAuthenticator {

	public static final Authenticator INSTANCE = new Authenticator();
	
	private Authenticator() {
	}

	public boolean authenticate(String id, String pwd) {
		return true;
	}
}

class Authorizer implements IAuthorizer {
	
	public static final Authorizer INSTANCE = new Authorizer();
	
	private Authorizer() {
	}

	public List<Authority> authorize(String id) {
		return Arrays.asList(Authority.Scan, Authority.Print, Authority.Copy);
	}
}

enum Authority {
	Scan, Print, Copy, Color_Print, Color_Copy, Fax
}

@FunctionalInterface
interface IAuthenticator {
	public boolean authenticate(String id, String pwd);
}

@FunctionalInterface
interface IAuthorizer {
	public List<Authority> authorize(String id);
}