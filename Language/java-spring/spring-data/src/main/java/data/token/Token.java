package data.token;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Token {

	private final String userId;
	private final String clientId;
	
	private final String accessToken;
	private final String refreshToken;
	
	private final String scopes; //between scopes separated by space
}
