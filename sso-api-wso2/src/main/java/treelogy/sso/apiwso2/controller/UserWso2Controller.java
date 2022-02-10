package treelogy.sso.apiwso2.controller;

import java.sql.Timestamp;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import treelogy.sso.apiwso2.model.UmUserModel;
import treelogy.sso.apiwso2.repository.UserWso2Repository;
import treelogy.sso.apiwso2.util.CryptSalt;
import treelogy.sso.apiwso2.util.FormatDateTime;
import treelogy.sso.apiwso2.util.ResponseConstructor;

@SuppressWarnings("rawtypes")
@RestController
@RequestMapping(value = "/wso2")
public class UserWso2Controller extends GenericController{

	@Autowired
	private UserWso2Repository userWso2Repository;
	
	@Autowired
	private CryptSalt cryptSalt;
			
	@GetMapping(value = "/search/{id}")
	@SuppressWarnings("unchecked")
	public ResponseEntity<UmUserModel> GetById(@PathVariable Long id) {

		UmUserModel user = userWso2Repository.findById(id).get();
		if (user == null) {

			return (ResponseEntity<UmUserModel>) ResponseEntity.notFound();
		}
		return ResponseEntity.ok(user);

	}

	@GetMapping(value = "/search/")
	@SuppressWarnings("unchecked")
	public ResponseEntity<Iterable<UmUserModel>> GetList() {

		Iterable<UmUserModel> user = userWso2Repository.findAll();
		if (user == null) {

			return (ResponseEntity<Iterable<UmUserModel>>) ResponseEntity.notFound();
		}
		return ResponseEntity.ok(user);

	}


	@SuppressWarnings("unchecked")
	@PostMapping(value = "/", produces = "application/json")
	public ResponseEntity<String> Post(@RequestBody UmUserModel user) throws Exception {

		try {
			
			user.setUmChangedTime(GetTimeStampNow());
			user.setUmUserPassword(cryptSalt.Encrypt(user.getUmUserPassword()));
			
			UmUserModel userSave = userWso2Repository.save(user);
			
			if (userSave == null) {

				throw new Exception("error saving data");
			}
			
			// RETORN SUCESS: assignment of success return values.

			httpStatus = HttpStatus.ACCEPTED;
			respCode   = "1";
			result     = BuilderData(userSave, new ArrayList<UmUserModel>());
			
			// RESPONSE: builder response API Generic
			
			String JSONBody = ResponseBuilder(respCode, httpStatus, result, new String());
			return new ResponseEntity<String>(JSONBody, httpStatus);
			
		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
			msgError = e.getMessage();
			
			// RETORN ERROR: assignment of success return values.
			
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			respCode   = "2";
			
			// RESPONSE: builder response API Generic
			
			String JSONBody = ResponseBuilder(respCode, httpStatus, new ArrayList<UmUserModel>(), msgError);
			
			return new ResponseEntity<String>(JSONBody, httpStatus);
			
		}
		


	}

}
