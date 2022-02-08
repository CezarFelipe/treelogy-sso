package treelogy.sso.apiwso2.controller;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import treelogy.sso.apiwso2.model.UmUser;
import treelogy.sso.apiwso2.repository.UserWso2Repository;
import treelogy.sso.apiwso2.util.CryptSalt;
import treelogy.sso.apiwso2.util.FormatDateTime;

@RestController
@RequestMapping(value = "/wso2")
public class UserWso2Controller {

	@Autowired
	private UserWso2Repository userWso2Repository;
	
	@Autowired
	private CryptSalt cryptSalt;
	
	@Autowired
	private FormatDateTime formatDateTime;
	
	@GetMapping(value = "/search/{id}")
	@SuppressWarnings("unchecked")
	public ResponseEntity<UmUser> GetById(@PathVariable Long id) {

		UmUser user = userWso2Repository.findById(id).get();
		if (user == null) {

			return (ResponseEntity<UmUser>) ResponseEntity.notFound();
		}
		return ResponseEntity.ok(user);

	}

	@GetMapping(value = "/search/")
	@SuppressWarnings("unchecked")
	public ResponseEntity<Iterable<UmUser>> GetList() {

		Iterable<UmUser> user = userWso2Repository.findAll();
		if (user == null) {

			return (ResponseEntity<Iterable<UmUser>>) ResponseEntity.notFound();
		}
		return ResponseEntity.ok(user);

	}


	@PostMapping(value = "/", produces = "application/json")
	public ResponseEntity<UmUser> Post(@RequestBody UmUser user) throws Exception {

		try {
			
			String strDateTime = formatDateTime.nowDateTime();
			System.out.println("strDateTime===="+strDateTime);
			
			Timestamp stampDateTime = formatDateTime.timeStamp(strDateTime);
			System.out.println("stampDateTime====="+stampDateTime);
			
			user.setUmChangedTime(stampDateTime);
			user.setUmUserPassword(cryptSalt.Encrypt(user.getUmUserPassword()));
			
			UmUser userSave = userWso2Repository.save(user);
			
			if (userSave == null) {

				throw new Exception("error saving data");
			}
			
			return new ResponseEntity<UmUser>(HttpStatus.OK);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
			String msgError = e.getMessage();
			
			return new ResponseEntity(msgError, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
