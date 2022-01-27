package treelogy.sso.oauth.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import treelogy.sso.oaut.model.User;
import treelogy.sso.oauth.service.UserService;

@RestController
@RequestMapping(value ="/users/")
public class UserController {

	private Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService service;
	
	@GetMapping(value = "search")
	public ResponseEntity<User> findByEmail(@RequestParam String email) throws IllegalAccessException{
				
		User user = service.findByEmail(email);
		return ResponseEntity.ok(user);
		
	}
}