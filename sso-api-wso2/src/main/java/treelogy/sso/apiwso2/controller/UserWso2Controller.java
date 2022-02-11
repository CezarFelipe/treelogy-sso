package treelogy.sso.apiwso2.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import treelogy.sso.apiwso2.dto.UserDto;
import treelogy.sso.apiwso2.model.MessageModel;
import treelogy.sso.apiwso2.model.UmUserModel;
import treelogy.sso.apiwso2.repository.UserWso2Repository;
import treelogy.sso.apiwso2.util.CryptSalt;

@SuppressWarnings("rawtypes")
@RestController
@RequestMapping(value = "/user")
public class UserWso2Controller extends GenericController {

	@Autowired
	private UserWso2Repository userWso2Repository;

	@Autowired
	private CryptSalt cryptSalt;

	@Autowired
	private UserDto userDto;

	@GetMapping(value = "/search/{id}", produces = "application/json")
	@SuppressWarnings({ "unchecked", "unused" })
	public ResponseEntity<String> GetById(@PathVariable Long id) {

		try {

			UmUserModel user = userWso2Repository.findById(id).get();

			userDto.setId(user.getUmUserId());
			userDto.setUsername(user.getUmUserName());
			userDto.setPassword(user.getUmUserPassword());

			// RETORN SUCESS: assignment of success return values.

			httpStatus = HttpStatus.OK;
			respCode = "1";

			if (user == null)
				httpStatus = HttpStatus.NOT_FOUND;

			result = BuilderData(userDto, new ArrayList<UmUserModel>());

			// RESPONSE: builder response API Generic

			String JSONBody = ResponseBuilder(respCode, httpStatus, result, new String());
			return new ResponseEntity<String>(JSONBody, httpStatus);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

			msgError = e.getMessage();

			// RETORN ERROR: assignment of success return values.

			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			respCode = "2";

			// RESPONSE: builder response API Generic

			String JSONBody = ResponseBuilder(respCode, httpStatus, new ArrayList<UmUserModel>(), msgError);

			return new ResponseEntity<String>(JSONBody, httpStatus);

		}

	}

	@GetMapping(value = "/search/", produces = "application/json")
	@SuppressWarnings("unchecked")
	public ResponseEntity<String> GetList() {

		try {

			Iterable<UmUserModel> users = userWso2Repository.findAll();

			List<UserDto> usersDto = new ArrayList<UserDto>();

			for (UmUserModel user : users) {

				UserDto userDto = new UserDto();

				userDto.setId(user.getUmUserId());
				userDto.setPassword(user.getUmUserPassword());
				userDto.setUsername(user.getUmUserName());
				usersDto.add(userDto);
			}

			// RETORN SUCESS: assignment of success return values.

			httpStatus = HttpStatus.CREATED;
			respCode = "1";
			
			ArrayList<UserDto> resultList = new ArrayList<UserDto>();

			for (UserDto userDTO : usersDto) {

				result = BuilderData(userDTO, resultList);
			}

			// RESPONSE: builder response API Generic

			String JSONBody = ResponseBuilder(respCode, httpStatus, result, new String());
			return new ResponseEntity<String>(JSONBody, httpStatus);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

			msgError = e.getMessage();

			// RETORN ERROR: assignment of success return values.

			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			respCode = "2";

			// RESPONSE: builder response API Generic

			String JSONBody = ResponseBuilder(respCode, httpStatus, new ArrayList<UmUserModel>(), msgError);

			return new ResponseEntity<String>(JSONBody, httpStatus);

		}

	}

	@SuppressWarnings("unchecked")
	@PostMapping(value = "/", produces = "application/json")
	public ResponseEntity<String> Post(@RequestBody UmUserModel user) throws Exception {

		try {

			user.setUmChangedTime(GetTimeStampNow());
			user.setUmUserPassword(cryptSalt.Encrypt(user.getUmUserPassword()));

			user.setUmChangedTime(GetTimeStampNow());
			UmUserModel userSave = userWso2Repository.save(user);

			if (userSave == null) {

				throw new Exception("error saving data");
			}

			// RETORN SUCESS: assignment of success return values.

			httpStatus = HttpStatus.CREATED;
			respCode = "1";
			result = BuilderData(userSave, new ArrayList<UmUserModel>());

			// RESPONSE: builder response API Generic

			String JSONBody = ResponseBuilder(respCode, httpStatus, result, new String());
			return new ResponseEntity<String>(JSONBody, httpStatus);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

			msgError = e.getMessage();

			// RETORN ERROR: assignment of success return values.

			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			respCode = "2";

			// RESPONSE: builder response API Generic

			String JSONBody = ResponseBuilder(respCode, httpStatus, new ArrayList<UmUserModel>(), msgError);

			return new ResponseEntity<String>(JSONBody, httpStatus);

		}

	}

}
