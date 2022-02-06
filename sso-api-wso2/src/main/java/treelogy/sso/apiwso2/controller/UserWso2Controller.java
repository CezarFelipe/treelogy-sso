package treelogy.sso.apiwso2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import treelogy.sso.apiwso2.model.UserWso2Model;
import treelogy.sso.apiwso2.repository.UserWso2Repository;

@RestController
@RequestMapping(value = "/api-wso2")
public class UserWso2Controller {
	
	@Autowired
	private UserWso2Repository userWso2Repository;
	
	@GetMapping(value = "/search")
	@SuppressWarnings("unchecked")
	public ResponseEntity<UserWso2Model> GetById(@PathVariable Long id){
		
		UserWso2Model user = userWso2Repository.findById(id).get();
		if (user == null) {
			
			return (ResponseEntity<UserWso2Model>) ResponseEntity.notFound();
		}
		return ResponseEntity.ok(user);
	
	}

}
