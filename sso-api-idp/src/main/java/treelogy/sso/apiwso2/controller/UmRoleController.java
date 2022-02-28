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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import treelogy.sso.apiwso2.dto.RoleDTO;
import treelogy.sso.apiwso2.model.Event;
import treelogy.sso.apiwso2.model.UmRole;
import treelogy.sso.apiwso2.model.UmUser;
import treelogy.sso.apiwso2.repository.UmRoleRepository;
import treelogy.sso.apiwso2.enumtype.ConstantException;

@SuppressWarnings("rawtypes")
@RestController
@RequestMapping(value = "/role")
public class UmRoleController extends GenericController {

	@Autowired
	private UmRoleRepository umRoleRepository;

	@SuppressWarnings("unused")
	private Logger logger = LoggerFactory.getLogger(UmRoleController.class);

	@GetMapping(value = "/search/{code}", produces = "application/json")
	@SuppressWarnings("unchecked")
	public ResponseEntity<String> GetByCode(@PathVariable Long code) {

		try {

			if (code == null) {
				throw new Exception(ConstantException.REQUIRFIELD + "code");
			}

			System.out.println("code: " + code);

			UmRole umRole = umRoleRepository.GetByCode(code);

			if (umRole == null) {

				throw new Exception("code " + code + ConstantException.NOTFOUND);
			}

			RoleDTO roleDTO = new RoleDTO();

			roleDTO.setDescription(umRole.getUmRoleName());
			roleDTO.setCode(umRole.getUmId());

			// RETORN SUCESS: assignment of success return values.

			httpStatus = HttpStatus.OK;
			respCode = "1";

			ArrayList<RoleDTO> result = new ArrayList<>();
			result = BuilderData(roleDTO, new ArrayList<RoleDTO>());

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
			System.out.println(msgError);
			if (msgError.toUpperCase().contains(ConstantException.NOTFOUND)) {

				httpStatus = HttpStatus.NOT_FOUND;
				respCode = "2";
			}
			// RESPONSE: builder response API Generic

			String JSONBody = BuilderResponse(respCode, httpStatus, new ArrayList<UmUser>(), msgError);

			return new ResponseEntity<String>(JSONBody, httpStatus);
		}

	}

	@GetMapping(value = "/search/", produces = "application/json")
	@SuppressWarnings("unchecked")
	public ResponseEntity<String> GetList() {

		try {

			List<UmRole> umRoles = umRoleRepository.findAll();

			if (umRoles.size() <= 0) {

				throw new Exception(ConstantException.NOTFOUND);
			}
			// RETORN SUCESS: assignment of success return values.

			httpStatus = HttpStatus.OK;
			respCode = "1";

			ArrayList<RoleDTO> roleDTOs = new ArrayList<>();

			for (UmRole role : umRoles) {

				RoleDTO roleDTO = new RoleDTO();

				roleDTO.setDescription(role.getUmRoleName());
				roleDTO.setCode(role.getUmId());

				roleDTOs.add(roleDTO);

			}

			ArrayList<RoleDTO> resultRoleDTO = new ArrayList<>();

			for (RoleDTO roleDTO : roleDTOs) {

				result = BuilderData(roleDTO, resultRoleDTO);
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
			respCode = "3";

			// RESPONSE: Exception Validations.
			System.out.println(msgError);

			if (msgError.contains(ConstantException.NOTFOUND)) {

				httpStatus = HttpStatus.NOT_FOUND;
				respCode = "2";
			}
			// RESPONSE: builder response API Generic

			String JSONBody = BuilderResponse(respCode, httpStatus, new ArrayList<UmUser>(), msgError);

			return new ResponseEntity<String>(JSONBody, httpStatus);
		}

	}

	@PostMapping(value = "/", produces = "application/json")
	@SuppressWarnings("unchecked")
	public ResponseEntity<String> Post(@RequestBody RoleDTO roleBody) {

		try {

			if (roleBody.getDescription().isEmpty() || roleBody.getDescription() == null) {
				throw new Exception(ConstantException.REQUIRFIELD + "name");
			}

			UmRole umRole = umRoleRepository.GetByCode(roleBody.getCode());

			if (umRole != null) {

				throw new Exception("Role " + roleBody.getCode() + ConstantException.NOTFOUND);
			}

			UmRole newUmRole = new UmRole();

			newUmRole.setUmRoleName(roleBody.getDescription());
			newUmRole.setUmTenantId(-1234);

			UmRole umRoleSave = umRoleRepository.save(newUmRole);

			if (umRoleSave == null) {

				throw new Exception(ConstantException.ERRORSAVE);
			}

			// RETORN SUCESS: assignment of success return values.

			httpStatus = HttpStatus.CREATED;
			respCode = "1";

			RoleDTO roleDTO = new RoleDTO();

			roleDTO.setDescription(umRoleSave.getUmRoleName());
			roleDTO.setCode(umRoleSave.getUmId());

			result = BuilderData(roleDTO, new ArrayList<RoleDTO>());

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

			if (msgError.toUpperCase().contains(ConstantException.ERRORSAVE)) {

				httpStatus = HttpStatus.BAD_REQUEST;
				respCode = "2";
			} else if (msgError.toUpperCase().contains(ConstantException.NOTFOUND)) {

				httpStatus = HttpStatus.NOT_FOUND;
				respCode = "2";

			}

			// RESPONSE: builder response API Generic

			String JSONBody = BuilderResponse(respCode, httpStatus, new ArrayList<Event>(), msgError);

			return new ResponseEntity<String>(JSONBody, httpStatus);
		}

	}

	@PutMapping(value = "/{code}", produces = "application/json")
	@SuppressWarnings("unchecked")
	public ResponseEntity<String> Update(@PathVariable Long code, @RequestBody RoleDTO roleBody) {

		try {
			if (code == null) {
				throw new Exception(ConstantException.REQUIRFIELD + "code");
			}

			UmRole umRole = umRoleRepository.GetByCode(code);

			if (umRole == null) {

				throw new Exception("code " + code + ConstantException.NOTFOUND);
			}

			umRole.setUmRoleName(roleBody.getDescription());

			UmRole umRoleUpdate = umRoleRepository.save(umRole);

			if (umRoleUpdate == null) {

				throw new Exception(ConstantException.ERRORSAVE);
			}

			RoleDTO roleDTO = new RoleDTO();

			roleDTO.setCode(umRoleUpdate.getUmId());
			roleDTO.setDescription(umRoleUpdate.getUmRoleName());

			// RETORN SUCESS: assignment of success return values.

			httpStatus = HttpStatus.OK;
			respCode = "1";

			result = BuilderData(roleDTO, new ArrayList<RoleDTO>());

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
			System.out.println(msgError);

			if (msgError.contains(ConstantException.ERRORSAVE)) {

				httpStatus = HttpStatus.BAD_REQUEST;
				respCode = "2";
			} else if (msgError.contains(ConstantException.NOTFOUND)) {

				httpStatus = HttpStatus.NOT_FOUND;
				respCode = "2";

			}
			// RESPONSE: builder response API Generic

			String JSONBody = BuilderResponse(respCode, httpStatus, new ArrayList<Event>(), msgError);

			return new ResponseEntity<String>(JSONBody, httpStatus);
		}

	}

	@GetMapping(value = "/integration/", produces = "application/json")
	@SuppressWarnings("unchecked")
	public ResponseEntity<String> GetByRead() {

		try {

			Iterable<UmRole> roles = umRoleRepository.GetRoleByIsRead();
			ArrayList<RoleDTO> roleDTOs = new ArrayList<>();
			for (UmRole role : roles) {

				RoleDTO roleDTO = new RoleDTO();

				roleDTO.setDescription(role.getUmRoleName());
				roleDTO.setCode(role.getUmId());

				roleDTOs.add(roleDTO);

			}

			ArrayList<RoleDTO> resultRoleDTO = new ArrayList<>();

			for (RoleDTO roleDTO : roleDTOs) {

				result = BuilderData(roleDTO, resultRoleDTO);
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

			// RESPONSE: Exception Validations.
			System.out.println(msgError);

			if (msgError.contains(ConstantException.ERRORSAVE)) {

				httpStatus = HttpStatus.BAD_REQUEST;
				respCode = "2";
			} else if (msgError.contains(ConstantException.NOTFOUND)) {

				httpStatus = HttpStatus.NOT_FOUND;
				respCode = "2";

			}
			// RESPONSE: builder response API Generic

			String JSONBody = BuilderResponse(respCode, httpStatus, new ArrayList<Event>(), msgError);

			return new ResponseEntity<String>(JSONBody, httpStatus);
		}

	}
}
