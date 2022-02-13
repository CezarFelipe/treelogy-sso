package treelogy.sso.apiwso2.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import treelogy.sso.apiwso2.model.UmUser;
import treelogy.sso.apiwso2.repository.UmUserRepository;
import treelogy.sso.apiwso2.util.CryptSalt;


@SuppressWarnings("rawtypes")
@RestController
@RequestMapping(value = "/user")
public class UmUserController extends GenericController {

	private static String ALREADYEXIST = "";

	@Autowired
	private UmUserRepository userWso2Repository;

	@Autowired
	private CryptSalt cryptSalt;

	@Autowired
	private UserDto userDto;

	@Autowired
	private UmUser umUserModel;
	
	private Logger logger = LoggerFactory.getLogger(UmUserController.class);
	
	@GetMapping(value = "/search/{code}", produces = "application/json")
	@SuppressWarnings({ "unchecked", "unused" })
	public ResponseEntity<String> GetById(@PathVariable String code) {

		try {

			
			UmUser user = userWso2Repository.GetByCode(code);

			userDto.setId(user.getUmUserId());
			userDto.setUsername(user.getUmUserName());
			userDto.setPassword(user.getUmUserPassword());

			// RETORN SUCESS: assignment of success return values.

			httpStatus = HttpStatus.OK;
			respCode = "1";

			if (user == null)
				httpStatus = HttpStatus.NOT_FOUND;

			result = BuilderData(userDto, new ArrayList<UmUser>());

			// RESPONSE: builder response API Generic

			String JSONBody = BuilderResponse(respCode, httpStatus, result, new String());
			return new ResponseEntity<String>(JSONBody, httpStatus);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

			msgError = e.getMessage();

			// RETORN ERROR: assignment of success return values.

			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			respCode = "2";

			// RESPONSE: builder response API Generic

			String JSONBody = BuilderResponse(respCode, httpStatus, new ArrayList<UmUser>(), msgError);

			return new ResponseEntity<String>(JSONBody, httpStatus);

		}

	}

	@GetMapping(value = "/search/", produces = "application/json")
	@SuppressWarnings("unchecked")
	public ResponseEntity<String> GetList() {

		try {

			Iterable<UmUser> users = userWso2Repository.findAll();

			List<UserDto> usersDto = new ArrayList<UserDto>();

			for (UmUser user : users) {

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

			String JSONBody = BuilderResponse(respCode, httpStatus, result, new String());
			return new ResponseEntity<String>(JSONBody, httpStatus);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

			msgError = e.getMessage();

			// RETORN ERROR: assignment of success return values.

			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			respCode = "2";

			// RESPONSE: builder response API Generic

			String JSONBody = BuilderResponse(respCode, httpStatus, new ArrayList<UmUser>(), msgError);

			return new ResponseEntity<String>(JSONBody, httpStatus);

		}

	}

	@SuppressWarnings("unchecked")
	@PostMapping(value = "/", produces = "application/json")
	public ResponseEntity<String> Post(@RequestBody UserDto userDto) throws Exception {

		try {

			UmUser userExists = userWso2Repository.GetByCode(userDto.getId());

			if (userExists != null) {
				throw new Exception(ALREADYEXIST);
			}
			umUserModel.setUmChangedTime(GetTimeStampNow());
			umUserModel.setUmUserPassword(cryptSalt.Encrypt(userDto.getPassword()));
			umUserModel.setUmUserId(userDto.getId());
			umUserModel.setUmUserName(userDto.getUsername());
			umUserModel.setUmTenantId(-1234);

			UmUser userSave = userWso2Repository.save(umUserModel);

			if (userSave == null) {

				throw new Exception("error saving data");
			}

			// RETORN SUCESS: assignment of success return values.

			httpStatus = HttpStatus.CREATED;
			respCode = "1";
			result = BuilderData(userSave, new ArrayList<UmUser>());

			// RESPONSE: builder response API Generic

			String JSONBody = BuilderResponse(respCode, httpStatus, result, new String());
			return new ResponseEntity<String>(JSONBody, httpStatus);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			msgError = e.getMessage();
			
			// RETORN ERROR: assignment of success return values.

			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			respCode = "2";

			// RESPONSE: Exception Validations.
			
			if (msgError.toUpperCase().contains(ALREADYEXIST)) {

				httpStatus = HttpStatus.BAD_REQUEST;
				respCode = "3";
			}

			// RESPONSE: builder response API Generic

			String JSONBody = BuilderResponse(respCode, httpStatus, new ArrayList<UmUser>(), msgError);

			return new ResponseEntity<String>(JSONBody, httpStatus);

		}

	}

}
