package configs.jms;

import java.io.Serializable;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public final class Client implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 83238131979783483L;

	private String clientId;
	private String name;

	private String role;

	private String redirectUrl;
	private String scopes;
}
