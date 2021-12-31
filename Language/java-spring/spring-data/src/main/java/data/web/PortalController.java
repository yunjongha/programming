package data.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import data.entity.Client;
import data.jms.PortalMessagingService;

@RestController
@RequestMapping(path = "/portal", produces = "application/json")
@CrossOrigin("*")
public class PortalController {

	@Autowired
	private PortalMessagingService portalService;

	@GetMapping
	public String get() {
		System.out.println("GET /portal");
		
		return "successful!";
	}
	
	@PostMapping("/client/add")
	public String add(@RequestParam Map<String,String> params) {
		System.out.println("POST /portal/client/add");
		
		Client client = new Client();
		client.setClientId(params.get("clientId"));
		client.setName(params.get("name"));
		client.setRole(params.get("role"));
		client.setRedirectUrl(params.get("redirectUrl"));
		client.setScopes(params.get("scopes"));
	
		portalService.sendClient(client);
		
		return "added";
	}
	
}
