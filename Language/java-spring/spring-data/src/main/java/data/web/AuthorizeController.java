package data.web;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Random;
import java.util.random.RandomGenerator;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import data.entity.AuthRequest;
import data.entity.Token;
import data.mem.MemoryRepository;

@RestController
@RequestMapping(path = "/oauth/2.0", produces = "application/json")
@CrossOrigin("*")
public class AuthorizeController {

	@Autowired
	private MemoryRepository memRepo;

	/**
	 * 
	 * @param response
	 * @param req
	 * @return
	 */
	@GetMapping("/authorize")
	public void authorize(HttpServletResponse response, AuthRequest req) {
		System.out.printf("req: %s\n", req);

		String reqId = requestId();
		Cookie reqCookie = new Cookie("reqId", reqId);
		response.addCookie(reqCookie);

		memRepo.<AuthRequest>put(reqId, req);
		
		try {
			response.sendRedirect("http://localhost:8080/oauth/internal/login");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String requestId() {
		long value = new Random().nextLong();
		return Long.toHexString(value);
	}

	/**
	 * 
	 * @return
	 */
	@GetMapping("/token")
	public ResponseEntity<Token> getToken() {

		String userId = "jongha";
		String clientId = "client-1234";

		String accessToken = "access-1234";
		String refreshToken = "refresh-1234";
		String scopes = "bank.list bank.loan bank.query";

		return new ResponseEntity<>(new Token(userId, clientId, accessToken, refreshToken, scopes), HttpStatus.OK);
	}
}
