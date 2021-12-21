package mydata.auth.web;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author yunjo
 *
 */
@RestController
@RequestMapping(path = "/authorize", produces = "application/json")
@CrossOrigin(origins = "*")
public final class AuthorizeController {

	@GetMapping
	@ResponseBody
	public String authorize(@RequestParam String client_id, @RequestParam String redirect_uri,
							@RequestParam String org_code, @RequestParam String state) {
		
		StringBuilder params = new StringBuilder();
		params.append("client_id: ");
		params.append(client_id);
		params.append(", redirect_uri: ");
		params.append(redirect_uri);
		params.append(", org_code: ");
		params.append(org_code);
		params.append(", state: ");
		params.append(state);
		
		System.out.println("thread name: " + Thread.currentThread().getName());

		new Throwable().printStackTrace();
		
		return params.toString();
	}
}
