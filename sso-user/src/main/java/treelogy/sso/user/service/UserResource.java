package treelogy.sso.user.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import treelogy.sso.user.model.User;
import treelogy.sso.user.repository.UserRepository;


@CrossOrigin
@RestController
@RequestMapping(value = "/users")
public class UserResource {
	
	
	private Logger logger = LoggerFactory.getLogger(UserResource.class);
	
	@Autowired
	private UserRepository repository;

	@GetMapping(value = "/{id}")
	public ResponseEntity<User> findId(@PathVariable Long id) {

		User obj = repository.findById(id).get();
		return ResponseEntity.ok(obj);
	}

	@GetMapping(value = "/search")
	public ResponseEntity<User> findByEmail(@RequestParam String email) {

		User obj = repository.findByEmail(email);
		return ResponseEntity.ok(obj);
	}
	
}
