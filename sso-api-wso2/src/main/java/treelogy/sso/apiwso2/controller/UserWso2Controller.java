package treelogy.sso.apiwso2.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import treelogy.sso.apiwso2.model.UmUser;
import treelogy.sso.apiwso2.repository.UserWso2Repository;

@RestController
@RequestMapping(value = "/wso2")
public class UserWso2Controller {
	
	@Autowired
	private UserWso2Repository userWso2Repository;
	
	@GetMapping(value = "/search/{id}")
	@SuppressWarnings("unchecked")
	public ResponseEntity<UmUser> GetById(@PathVariable Long id){
		
		UmUser user = userWso2Repository.findById(id).get();
		if (user == null) {
			
			return (ResponseEntity<UmUser>) ResponseEntity.notFound();
		}
		return ResponseEntity.ok(user);
	
	}
	
	@GetMapping(value = "/search/")
	@SuppressWarnings("unchecked")
	public ResponseEntity<Iterable<UmUser>> GetList(){
		
		Iterable<UmUser> user = userWso2Repository.findAll();
		if (user == null) {
			
			return (ResponseEntity<Iterable<UmUser>>) ResponseEntity.notFound();
		}
		return ResponseEntity.ok(user);
	
	}

}
