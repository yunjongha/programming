package data.web;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import data.db.TokenRepository;

@RestController
@RequestMapping(path = "/token", produces = "application/json")
@CrossOrigin("*")
public class TokenController {

	private final TokenRepository tokenRepo;
	
	public TokenController(TokenRepository tokenRepo) {
		this.tokenRepo = tokenRepo;
	}
	
	@GetMapping
	public String getToken() {
	}
	
	@PostMapping
	public void setToken(@RequestBody String body) {

		tokenRepo.save(null);
	}
}
