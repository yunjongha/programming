package data.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public final class AuthRequest {
	
	private final String client_id;
	private final String redirect_uri;
	private final String org_code;
	private final String state;
}
