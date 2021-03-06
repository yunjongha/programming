package data.web;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import data.entity.AuthRequest;
import data.mem.MemoryRepository;

@RestController
@RequestMapping(path = "/oauth/internal", produces = "application/json")
@CrossOrigin("*")
public class AuthenticateController {

	@Autowired
	private MemoryRepository memRepo;
	
	@GetMapping("/login")
	//@HystrixCommand(fallbackMethod = "loginError")
	public void login(HttpServletRequest request, HttpServletResponse response) {
	
		Cookie[] cookies = request.getCookies();
		if(cookies == null) {
			System.out.println("GET /login, no cookies");
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}

		Optional<Cookie> reqCookie = Stream.of(cookies).filter(x -> x.getName().equals("reqId")).findAny();
		if(reqCookie.isPresent() == false) {
			System.out.println("GET /login, no reqId cookies");
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}		
		
		AuthRequest authReq = memRepo.<AuthRequest>get(reqCookie.get().getValue(), AuthRequest.class);

		System.out.println("GET /login, found the corresponding auth request: " + authReq);
		try {
			response.sendRedirect("http://localhost:3001/login");
		} catch (IOException e) {
			e.printStackTrace();
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
	}
	
	private void loginError(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("GET /login, login error occurred");
		response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	}
	
}
