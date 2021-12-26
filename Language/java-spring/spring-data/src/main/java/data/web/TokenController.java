package data.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import data.db.TokenRepository;
import data.entity.Token;

@RestController
@RequestMapping(path = "/oauth/2.0/token", produces = "application/json")
@CrossOrigin("*")
public class TokenController {

	private final TokenRepository tokenRepo;
	
	public TokenController(TokenRepository tokenRepo) {
		this.tokenRepo = tokenRepo;
	}
	
	@GetMapping
	public ResponseEntity<Token> getToken() {
		
		String userId = "jongha";
		String clientId = "client-1234";
		
		String accessToken = "access-1234";
		String refreshToken = "refresh-1234";
		String scopes = "bank.list bank.loan bank.query";
		
		return new ResponseEntity<>(new Token(userId, clientId, accessToken, refreshToken, scopes), HttpStatus.OK);
	}
	
}
