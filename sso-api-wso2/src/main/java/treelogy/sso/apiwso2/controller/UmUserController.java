package treelogy.sso.apiwso2.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import treelogy.sso.apiwso2.dto.AssignDto;
import treelogy.sso.apiwso2.dto.RoleDTO;
import treelogy.sso.apiwso2.dto.UserDto;
import treelogy.sso.apiwso2.model.Event;
import treelogy.sso.apiwso2.model.UmRole;
import treelogy.sso.apiwso2.model.UmUser;
import treelogy.sso.apiwso2.model.UmUserRole;
import treelogy.sso.apiwso2.repository.UmRoleRepository;
import treelogy.sso.apiwso2.repository.UmUserRepository;
import treelogy.sso.apiwso2.util.CryptSalt;

@SuppressWarnings("rawtypes")
@RestController
@RequestMapping(value = "/user")
public class UmUserController extends GenericController {

	private static String ERRORSAVE = "Error saving record!";

	private static String ALREADYEXIST = "Already registered!";

	private static String NOTFOUND = " not found";

	private static String REQUIRFIELD = "Required field: ";

	@Autowired
	private UmUserRepository userWso2Repository;

	@Autowired
	private UmRoleRepository umRoleRepository;

	@Autowired
	private CryptSalt cryptSalt;

	@Autowired
	private UserDto userDto;

	@Autowired
	private UmUser umUserModel;

	private Logger logger = LoggerFactory.getLogger(UmUserController.class);

	@GetMapping(value = "/search/{code}", produces = "application/json")
	@SuppressWarnings({ "unchecked", "unused" })
	public ResponseEntity<String> GetByCode(@PathVariable String code) {

		try {

			if (code.isEmpty() || code == null) {
				throw new Exception(REQUIRFIELD + "code");
			}

			UmUser user = userWso2Repository.GetByCode(code);

			if (user == null) {

				throw new Exception("code " + code + NOTFOUND);
			}

			UserDto userDto = new UserDto();

			userDto.setCode(user.getUmUserId());
			userDto.setUsername(user.getUmUserName());
			userDto.setPassword(user.getUmUserPassword());

			UmUserRole umUserRole = userWso2Repository.GetAssignByUser(user);

			if (umUserRole != null) {

				UmRole role = umRoleRepository.findById(umUserRole.getUmRole().getUmId()).get();

				RoleDTO roleDTO = new RoleDTO();

				roleDTO.setCode(role.getUmId());
				roleDTO.setDescription(role.getUmRoleName());

				userDto.setRoleDTO(roleDTO);
			}

			result = BuilderData(userDto, new ArrayList<UmUser>());

			// RETORN SUCESS: assignment of success return values.

			httpStatus = HttpStatus.OK;
			respCode = "1";

			// RESPONSE: builder response API Generic

			String JSONBody = BuilderResponse(respCode, httpStatus, result, new String());
			return new ResponseEntity<String>(JSONBody, httpStatus);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

			msgError = e.getMessage();

			// RETORN ERROR: assignment of success return values.

			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			respCode = "3";

			// RESPONSE: builder response API Generic
			System.out.println(msgError);
			if (msgError.toUpperCase().contains(NOTFOUND)) {

				httpStatus = HttpStatus.NOT_FOUND;
				respCode = "2";
			} else if (msgError.toUpperCase().contains(REQUIRFIELD)) {

				httpStatus = HttpStatus.BAD_REQUEST;
				respCode = "2";
			}
			String JSONBody = BuilderResponse(respCode, httpStatus, new ArrayList<UmUser>(), msgError);

			return new ResponseEntity<String>(JSONBody, httpStatus);

		}

	}

	@GetMapping(value = "/search/", produces = "application/json")
	@SuppressWarnings("unchecked")
	public ResponseEntity<String> GetList() {

		try {

			List<UmUser> users = userWso2Repository.findAll();

			if (users.size() <= 0) {

				throw new Exception(NOTFOUND);
			}

			List<UserDto> usersDto = new ArrayList<UserDto>();

			for (UmUser user : users) {

				UserDto userDto = new UserDto();

				userDto.setCode(user.getUmUserId());
				userDto.setPassword(user.getUmUserPassword());
				userDto.setUsername(user.getUmUserName());

				UmUserRole umUserRole = userWso2Repository.GetAssignByUser(user);

				if (umUserRole != null) {

					UmRole role = umRoleRepository.findById(umUserRole.getUmRole().getUmId()).get();

					RoleDTO roleDTO = new RoleDTO();

					roleDTO.setCode(role.getUmId());
					roleDTO.setDescription(role.getUmRoleName());

					userDto.setRoleDTO(roleDTO);
				}

				usersDto.add(userDto);
			}

			ArrayList<UserDto> resultList = new ArrayList<UserDto>();

			for (UserDto userDTO : usersDto) {

				result = BuilderData(userDTO, resultList);
			}

			// RETORN SUCESS: assignment of success return values.

			httpStatus = HttpStatus.OK;
			respCode = "1";

			// RESPONSE: builder response API Generic

			String JSONBody = BuilderResponse(respCode, httpStatus, result, new String());
			return new ResponseEntity<String>(JSONBody, httpStatus);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

			msgError = e.getMessage();

			// RETORN ERROR: assignment of success return values.

			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			respCode = "3";

			// RESPONSE: builder response API Generic
			System.out.println(msgError);
			if (msgError.toUpperCase().contains(NOTFOUND)) {

				httpStatus = HttpStatus.NOT_FOUND;
				respCode = "2";
			} else if (msgError.toUpperCase().contains(REQUIRFIELD)) {

				httpStatus = HttpStatus.BAD_REQUEST;
				respCode = "2";
			}
			// RESPONSE: builder response API Generic

			String JSONBody = BuilderResponse(respCode, httpStatus, new ArrayList<UmUser>(), msgError);

			return new ResponseEntity<String>(JSONBody, httpStatus);

		}

	}

	@SuppressWarnings("unchecked")
	@PostMapping(value = "/", produces = "application/json")
	public ResponseEntity<String> Post(@RequestBody UserDto userDto) throws Exception {

		try {

			UmUser userExists = userWso2Repository.GetByCode(userDto.getCode());

			if (userExists != null) {
				throw new Exception(ALREADYEXIST);
			}

			UmUser umUserModel = new UmUser();

			umUserModel.setUmChangedTime(GetTimeStampNow());
			umUserModel.setUmUserPassword(cryptSalt.Encrypt(userDto.getPassword()));
			umUserModel.setUmUserId(userDto.getCode());
			umUserModel.setUmUserName(userDto.getUsername());
			umUserModel.setUmTenantId(-1234);

			UmUser userSave = userWso2Repository.save(umUserModel);

			userDto.setCode(userSave.getUmUserId());
			userDto.setUsername(userSave.getUmUserName());
			userDto.setPassword(userSave.getUmUserPassword());
			userDto.setRequire_change(userSave.getUmRequireChange());
			userDto.setChanged_time(userSave.getUmChangedTime());

			// RETORN SUCESS: assignment of success return values.

			httpStatus = HttpStatus.CREATED;
			respCode = "1";
			result = BuilderData(userDto, new ArrayList<UserDto>());

			// RESPONSE: builder response API Generic

			String JSONBody = BuilderResponse(respCode, httpStatus, result, new String());
			return new ResponseEntity<String>(JSONBody, httpStatus);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			msgError = e.getMessage();

			// RETORN ERROR: assignment of success return values.

			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			respCode = "3";

			// RESPONSE: Exception Validations.

			if (msgError.toUpperCase().contains(ALREADYEXIST)) {

				httpStatus = HttpStatus.BAD_REQUEST;
				respCode = "2";
			} else if (msgError.toUpperCase().contains(NOTFOUND)) {

				httpStatus = HttpStatus.NOT_FOUND;
				respCode = "2";
			} else if (msgError.toUpperCase().contains(REQUIRFIELD)) {

				httpStatus = HttpStatus.BAD_REQUEST;
				respCode = "2";
			}

			// RESPONSE: builder response API Generic

			String JSONBody = BuilderResponse(respCode, httpStatus, new ArrayList<UserDto>(), msgError);

			return new ResponseEntity<String>(JSONBody, httpStatus);

		}

	}

	@PutMapping(value = "/{code}", produces = "application/json")
	@SuppressWarnings("unchecked")
	public ResponseEntity<String> Update(@PathVariable String code, @RequestBody UserDto userDto) {

		try {
			if (code.isEmpty() || code == null) {
				throw new Exception(REQUIRFIELD + "code");
			}

			UmUser userSelect = userWso2Repository.GetByCode(code);

			if (userSelect == null) {

				throw new Exception("code " + code + NOTFOUND);
			}

			umUserModel.setUmId(userSelect.getUmId());
			umUserModel.setUmUserId(userDto.getCode());
			umUserModel.setUmUserPassword(userDto.getPassword());
			umUserModel.setUmRequireChange(userDto.getRequire_change());
			umUserModel.setUmChangedTime(GetTimeStampNow());

			UmUser userUpdate = userWso2Repository.save(umUserModel);

			userDto.setCode(userUpdate.getUmUserId());
			userDto.setUsername(userUpdate.getUmUserName());
			userDto.setPassword(userUpdate.getUmUserPassword());
			userDto.setChanged_time(userUpdate.getUmChangedTime());
			userDto.setRequire_change(userUpdate.getUmRequireChange());

			// RETORN SUCESS: assignment of success return values.

			httpStatus = HttpStatus.OK;
			respCode = "1";

			result = BuilderData(userDto, new ArrayList<UserDto>());

			// RESPONSE: builder response API Generic

			String JSONBody = BuilderResponse(respCode, httpStatus, result, new String());
			return new ResponseEntity<String>(JSONBody, httpStatus);

		} catch (Exception e) {

			// TODO: handle exception
			e.printStackTrace();

			msgError = e.getMessage();

			// RETORN ERROR: assignment of success return values.

			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			respCode = "3";

			// RESPONSE: Exception Validations.

			if (msgError.toUpperCase().contains(ALREADYEXIST)) {

				httpStatus = HttpStatus.BAD_REQUEST;
				respCode = "2";
			} else if (msgError.toUpperCase().contains(NOTFOUND)) {

				httpStatus = HttpStatus.NOT_FOUND;
				respCode = "2";
			} else if (msgError.toUpperCase().contains(REQUIRFIELD)) {

				httpStatus = HttpStatus.BAD_REQUEST;
				respCode = "2";
			}
			// RESPONSE: builder response API Generic

			String JSONBody = BuilderResponse(respCode, httpStatus, new ArrayList<UserDto>(), msgError);

			return new ResponseEntity<String>(JSONBody, httpStatus);
		}

	}

	@DeleteMapping(value = "/{code}", produces = "application/json")
	@SuppressWarnings("unchecked")
	public ResponseEntity<String> Delete(@PathVariable String code) {

		try {

			if (code.isEmpty() || code == null) {
				throw new Exception(REQUIRFIELD + "code");
			}

			UmUser umUser = userWso2Repository.GetByCode(code);

			if (umUser == null) {

				throw new Exception("code " + code + NOTFOUND);
			}

			userWso2Repository.deleteById(umUser.getUmId());

			// RETORN SUCESS: assignment of success return values.

			httpStatus = HttpStatus.OK;
			respCode = "1";

			// RESPONSE: builder response API Generic

			String JSONBody = BuilderResponse(respCode, httpStatus, new ArrayList<Event>(), new String());
			return new ResponseEntity<String>(JSONBody, httpStatus);

		} catch (Exception e) {

			// TODO: handle exception
			e.printStackTrace();

			msgError = e.getMessage();

			// RETORN ERROR: assignment of success return values.

			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			respCode = "2";

			// RESPONSE: Exception Validations.
			System.out.println(msgError);

			if (msgError.contains(ERRORSAVE)) {

				httpStatus = HttpStatus.BAD_REQUEST;
				respCode = "2";
			} else if (msgError.contains(NOTFOUND)) {

				httpStatus = HttpStatus.NOT_FOUND;
				respCode = "2";

			}
			// RESPONSE: builder response API Generic

			String JSONBody = BuilderResponse(respCode, httpStatus, new ArrayList<Event>(), msgError);

			return new ResponseEntity<String>(JSONBody, httpStatus);
		}

	}

	@PostMapping(value = "/assign/", produces = "application/json")
	@SuppressWarnings("unchecked")
	public ResponseEntity<String> AssignToUser(@RequestBody AssignDto assignDto) {

		try {

			if (assignDto.getRoleCode() == null) {
				throw new Exception(REQUIRFIELD + "code role");
			} else if (assignDto.getUserCode().isEmpty() || assignDto.getUserCode() == null) {
				throw new Exception(REQUIRFIELD + "code user");
			}

			UmUser umUser = userWso2Repository.GetByCode(assignDto.getUserCode());

			if (umUser == null) {

				throw new Exception("code user" + assignDto.getUserCode() + NOTFOUND);
			}

			UmRole umRole = umRoleRepository.GetByCode(assignDto.getRoleCode());

			if (umRole == null) {

				throw new Exception("code role" + assignDto.getRoleCode() + NOTFOUND);
			}

			UmUserRole umUserRole = userWso2Repository.GetAssignByUser(umUser);

			if (umUserRole != null) {
				System.out.println("UPDATE");
				Integer umUserRoleSave = userWso2Repository.AssignRoleToUserUpdate(umRole, umUser);
				if (umUserRoleSave == null) {

					throw new Exception(ERRORSAVE);
				}
			} else {
				System.out.println("CREATE");
				userWso2Repository.AssignRoleToUser(umRole.getUmId(), umUser.getUmId(), -1234);
			}

			// RETORN SUCESS: assignment of success return values.

			httpStatus = HttpStatus.CREATED;
			respCode = "1";

			// RESPONSE: builder response API Generic

			String JSONBody = BuilderResponse(respCode, httpStatus, new ArrayList<>(), new String());
			return new ResponseEntity<String>(JSONBody, httpStatus);

		} catch (Exception e) {

			// TODO: handle exception
			e.printStackTrace();

			msgError = e.getMessage();

			// RETORN ERROR: assignment of success return values.

			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			respCode = "2";

			// RESPONSE: Exception Validations.
			System.out.println(msgError);

			if (msgError.contains(ERRORSAVE)) {

				httpStatus = HttpStatus.BAD_REQUEST;
				respCode = "2";
			} else if (msgError.contains(NOTFOUND)) {

				httpStatus = HttpStatus.NOT_FOUND;
				respCode = "2";

			}
			// RESPONSE: builder response API Generic

			String JSONBody = BuilderResponse(respCode, httpStatus, new ArrayList<Event>(), msgError);

			return new ResponseEntity<String>(JSONBody, httpStatus);
		}

	}
}
