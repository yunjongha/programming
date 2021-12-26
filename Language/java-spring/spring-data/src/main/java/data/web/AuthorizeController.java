package data.web;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import data.entity.AuthRequest;

@RestController()
@RequestMapping(path = "/oauth/2.0/authorize", produces = "application/json")
@CrossOrigin("*")
public class AuthorizeController {

	@GetMapping
	public String authorize(AuthRequest req) {
		System.out.printf("req: %s\n", req);
		
		return "http://";
	}
}
