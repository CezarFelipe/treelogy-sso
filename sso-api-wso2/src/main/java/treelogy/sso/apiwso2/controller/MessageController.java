package treelogy.sso.apiwso2.controller;

import java.util.ArrayList;
import java.util.List;

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

import treelogy.sso.apiwso2.model.MessageModel;
import treelogy.sso.apiwso2.model.TypeMessageModel;
import treelogy.sso.apiwso2.model.UmUserModel;
import treelogy.sso.apiwso2.repository.MessageRepository;
import treelogy.sso.apiwso2.repository.TypeMessageRepository;

@SuppressWarnings("rawtypes")
@RestController
@RequestMapping(value = "/message")
public class MessageController extends GenericController {

	@Autowired
	private MessageRepository messageRepository;

	@Autowired
	private TypeMessageRepository typeMessageRepository;

	@GetMapping(value = "/search/{id}", produces = "application/json")
	@SuppressWarnings("unchecked")
	public ResponseEntity<String> GetById(@PathVariable Long id) {

		try {
			MessageModel message = messageRepository.findById(id).get();

			// RETORN SUCESS: assignment of success return values.

			httpStatus = HttpStatus.ACCEPTED;
			respCode = "1";

			if (message == null) {

				httpStatus = HttpStatus.NOT_FOUND;
			}

			result = BuilderData(message, new ArrayList<UmUserModel>());

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
			List<MessageModel> messages = messageRepository.findAll();

			// RETORN SUCESS: assignment of success return values.

			httpStatus = HttpStatus.ACCEPTED;
			respCode = "1";

			if (messages.size()<=0) {

				httpStatus = HttpStatus.NOT_FOUND;
			}
			
			for(MessageModel msg: messages){
					result = BuilderData(msg, new ArrayList<MessageModel>());
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
	@PostMapping(value = "/", produces = "application/json")
	@SuppressWarnings("unchecked")
	public ResponseEntity<String> Create(@RequestBody MessageModel message) {

		try {

			TypeMessageModel typeMessage = typeMessageRepository
					.FindTypeMessageByCode(message.getTypeMessage().getCode());
			message.setTypeMessage(typeMessage);
			message.setCreatedAt(GetTimeStampNow());
			MessageModel messageSave = messageRepository.save(message);

			if (messageSave == null) {

				throw new Exception("error saving data");
			}

			// RETORN SUCESS: assignment of success return values.

			httpStatus = HttpStatus.CREATED;
			respCode = "1";

			result = BuilderData(messageSave, new ArrayList<UmUserModel>());

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

			String JSONBody = ResponseBuilder(respCode, httpStatus, new ArrayList<MessageModel>(), msgError);

			return new ResponseEntity<String>(JSONBody, httpStatus);
		}

	}

	@PutMapping(value = "/{id}",produces = "application/json")
	@SuppressWarnings("unchecked")
	public ResponseEntity<String> Update(@PathVariable String code, @RequestBody MessageModel message) {

		try {

			MessageModel messageUpdate = messageRepository.FindMessageByCode(code);
			message.setId(messageUpdate.getId());
			MessageModel messageSave = messageRepository.save(message);

			if (messageSave == null) {

				throw new Exception("error saving data");
			}

			// RETORN SUCESS: assignment of success return values.

			httpStatus = HttpStatus.ACCEPTED;
			respCode = "1";

			result = BuilderData(messageSave, new ArrayList<MessageModel>());

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

			String JSONBody = ResponseBuilder(respCode, httpStatus, new ArrayList<MessageModel>(), msgError);

			return new ResponseEntity<String>(JSONBody, httpStatus);
		}

	}

	@DeleteMapping(value = "/{id}",produces = "application/json")
	@SuppressWarnings("unchecked")
	public ResponseEntity<String> Delete(@PathVariable String code) {

		try {
			MessageModel messageDelete = messageRepository.FindMessageByCode(code);

			messageRepository.deleteById(messageDelete.getId());

			// RETORN SUCESS: assignment of success return values.

			httpStatus = HttpStatus.ACCEPTED;
			respCode = "3";

			// RESPONSE: builder response API Generic

			String JSONBody = ResponseBuilder(respCode, httpStatus, new ArrayList<MessageModel>(), new String());
			return new ResponseEntity<String>(JSONBody, httpStatus);

		} catch (Exception e) {

			// TODO: handle exception
			e.printStackTrace();

			msgError = e.getMessage();

			// RETORN ERROR: assignment of success return values.

			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			respCode = "2";

			// RESPONSE: builder response API Generic

			String JSONBody = ResponseBuilder(respCode, httpStatus, new ArrayList<MessageModel>(), msgError);

			return new ResponseEntity<String>(JSONBody, httpStatus);
		}

	}
}
