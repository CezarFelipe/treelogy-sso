package treelogy.sso.apiwso2.controller;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import treelogy.sso.apiwso2.dto.RoleDTO;
import treelogy.sso.apiwso2.dto.UserDto;
import treelogy.sso.apiwso2.enumtype.ConstantException;
import treelogy.sso.apiwso2.model.Situation;
import treelogy.sso.apiwso2.model.UmRole;
import treelogy.sso.apiwso2.model.UmUser;
import treelogy.sso.apiwso2.model.UmUserRole;
import treelogy.sso.apiwso2.repository.SituationRepository;
import treelogy.sso.apiwso2.repository.UmRoleRepository;
import treelogy.sso.apiwso2.repository.UmUserRepository;


@SuppressWarnings("rawtypes")
@RestController
@RequestMapping(value = "/integration")
public class UserIntegrationController extends GenericController {

	@Autowired
	private SituationRepository situationRepository;
	
	@Autowired
	private UmUserRepository userWso2Repository;

	@Autowired
	private UmRoleRepository umRoleRepository;

	@SuppressWarnings("unused")
	private Logger logger = LoggerFactory.getLogger(UserIntegrationController.class);

	@GetMapping(value = "/user/", produces = "application/json")
	@SuppressWarnings({ "unchecked" })
	public ResponseEntity<String> GetUserBySituation(@RequestParam(required = true, name = "situation") String situation) {

		try {

			Situation getSituation = situationRepository.GetSituationByName(situation);

			if (getSituation == null) {

				throw new Exception("situation " + situation + ConstantException.NOTFOUND);
			}
            
			System.out.println(getSituation.getDescription());
            
			Iterable<UmUser> umUserList = situationRepository.GetUserBySituation(getSituation);
			
			ArrayList<UserDto> resultList = new ArrayList<UserDto>();
			ArrayList<?> result = new ArrayList<>();
			
			for (UmUser user: umUserList) {
				
				UserDto userDto = new UserDto();
				
				userDto.setCode(user.getUmUserId());
				userDto.setUsername(user.getUmUserName());
				userDto.setPassword(user.getUmUserPassword());
				userDto.setRequire_change(user.getUmRequireChange());
				userDto.setChanged_time(user.getUmChangedTime());
				
				UmUserRole userAssign = userWso2Repository.GetAssignByUser(user);
				
				if (userAssign != null) {
					
					UmRole role = umRoleRepository.GetByCode(userAssign.getUmRole().getUmId());
					
					RoleDTO roleDTO = new RoleDTO();
					
					roleDTO.setCode(role.getUmId());
					roleDTO.setDescription(role.getUmRoleName());
					
					userDto.setRoleDTO(roleDTO);
				}
									
				result = BuilderData(userDto, resultList);
					
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
			if (msgError.contains(ConstantException.NOTFOUND)) {

				httpStatus = HttpStatus.NOT_FOUND;
				respCode = "2";
			} else if (msgError.toUpperCase().contains(ConstantException.REQUIRFIELD)) {

				httpStatus = HttpStatus.BAD_REQUEST;
				respCode = "2";
			}
			String JSONBody = BuilderResponse(respCode, httpStatus, new ArrayList<UmUser>(), msgError);

			return new ResponseEntity<String>(JSONBody, httpStatus);

		}

	}
	
	@PutMapping(value = "/user/", produces = "application/json")
	@SuppressWarnings({ "unchecked" })
	public ResponseEntity<String> UpdateUserSituation(@RequestParam(required = true, name = "situation") String situation, @RequestParam(required = true, name = "code") String codeUser) {

		try {

			Situation getSituation = situationRepository.GetSituationByName(situation);

			if (getSituation == null) {

				throw new Exception("situation " + situation + ConstantException.NOTFOUND);
			}
            
			System.out.println(getSituation.getDescription());
			
			UmUser umUser  = userWso2Repository.GetByCode(codeUser);

			if (umUser == null) {

				throw new Exception("code user " + codeUser + ConstantException.NOTFOUND);
			}
            
			System.out.println(codeUser);
            
			umUser.setSituation(getSituation);
			
			userWso2Repository.save(umUser);		

			// RETORN SUCESS: assignment of success return values.

			httpStatus = HttpStatus.OK;
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
			respCode = "3";

			// RESPONSE: builder response API Generic
			System.out.println(msgError);
			if (msgError.contains(ConstantException.NOTFOUND)) {

				httpStatus = HttpStatus.NOT_FOUND;
				respCode = "2";
			} else if (msgError.toUpperCase().contains(ConstantException.REQUIRFIELD)) {

				httpStatus = HttpStatus.BAD_REQUEST;
				respCode = "2";
			}
			String JSONBody = BuilderResponse(respCode, httpStatus, new ArrayList<UmUser>(), msgError);

			return new ResponseEntity<String>(JSONBody, httpStatus);

		}

	}
}
