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

import treelogy.sso.apiwso2.dto.EventDTO;
import treelogy.sso.apiwso2.dto.StatusDTO;
import treelogy.sso.apiwso2.model.Event;
import treelogy.sso.apiwso2.model.Status;
import treelogy.sso.apiwso2.model.UmUser;
import treelogy.sso.apiwso2.repository.EventRepository;
import treelogy.sso.apiwso2.repository.StatusRepository;


@SuppressWarnings("rawtypes")
@RestController
@RequestMapping(value = "/event")
public class EventController extends GenericController {

	private static String ERRORSAVE = "Error saving record!";

	private static String NOTFOUND = " not found";

	private static String REQUIRFIELD = "Required field: ";

	@Autowired
	private EventRepository eventRepository;

	@Autowired
	private StatusRepository statusRepository;

	@Autowired
	private EventDTO eventDTO;

	@Autowired
	private StatusDTO statusDTO;

	private Logger logger = LoggerFactory.getLogger(EventController.class);
	
	@GetMapping(value = "/search/{code}", produces = "application/json")
	@SuppressWarnings("unchecked")
	public ResponseEntity<String> GetByCode(@PathVariable String code) {

		try {

			if (code.isEmpty() || code == null) {
				throw new Exception(REQUIRFIELD + "code");
			}

			Event event = eventRepository.GetByCode(code);

			if (event == null) {

				throw new Exception("code " + code + NOTFOUND);
			}

			statusDTO.setCode(event.getCode());
			statusDTO.setDescription(event.getDescription());

			eventDTO.setCode(event.getCode());
			eventDTO.setDescription(event.getDescription());
			eventDTO.setStatus(statusDTO);

			// RETORN SUCESS: assignment of success return values.

			httpStatus = HttpStatus.OK;
			respCode = "1";

			result = BuilderData(eventDTO, new ArrayList<Event>());

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
			System.out.println(msgError);
			if (msgError.toUpperCase().contains(NOTFOUND)) {

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
			List<Event> events = eventRepository.findAll();

			if (events.size() <= 0) {

				throw new Exception(NOTFOUND);
			}
			// RETORN SUCESS: assignment of success return values.

			httpStatus = HttpStatus.OK;
			respCode = "1";

			ArrayList<EventDTO> eventDTOs = new ArrayList<>();

			for (Event event : events) {

				StatusDTO statusDTO = new StatusDTO();
				EventDTO eventDTO = new EventDTO();

				statusDTO.setCode(event.getStatus().getCode());
				statusDTO.setDescription(event.getStatus().getDescription());

				eventDTO.setCode(event.getCode());
				eventDTO.setDescription(event.getDescription());
				eventDTO.setStatus(statusDTO);

				eventDTOs.add(eventDTO);

			}

			ArrayList<EventDTO> resultEventDTO = new ArrayList<>();

			for (EventDTO eventDTO : eventDTOs) {

				result = BuilderData(eventDTO, resultEventDTO);
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

			// RESPONSE: Exception Validations.
			System.out.println(msgError);

			if (msgError.contains(NOTFOUND)) {

				httpStatus = HttpStatus.NOT_FOUND;
				respCode = "3";
			}
			// RESPONSE: builder response API Generic

			String JSONBody = BuilderResponse(respCode, httpStatus, new ArrayList<UmUser>(), msgError);

			return new ResponseEntity<String>(JSONBody, httpStatus);
		}

	}

	@PostMapping(value = "/", produces = "application/json")
	@SuppressWarnings("unchecked")
	public ResponseEntity<String> Post(@RequestBody Event eventBody) {

		try {

			Status status = statusRepository.GetByCodeIsActive(true, eventBody.getStatus().getCode());

			if (status == null) {

				throw new Exception("status " + eventBody.getStatus().getCode() + NOTFOUND);
			}

			eventBody.setStatus(status);
			eventBody.setCreatedAt(GetTimeStampNow());
			eventBody.setActive(true);
			Event eventSave = eventRepository.save(eventBody);

			if (eventSave == null) {

				throw new Exception(ERRORSAVE);
			}

			// RETORN SUCESS: assignment of success return values.

			httpStatus = HttpStatus.OK;
			respCode = "1";

			result = BuilderData(eventSave, new ArrayList<Event>());

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

			if (msgError.toUpperCase().contains(ERRORSAVE)) {

				httpStatus = HttpStatus.BAD_REQUEST;
				respCode = "3";
			} else if (msgError.toUpperCase().contains(NOTFOUND)) {

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
	public ResponseEntity<String> Update(@PathVariable String code, @RequestBody Event eventBody) {

		try {
			if (code.isEmpty() || code == null) {
				throw new Exception(REQUIRFIELD + "code");
			}

			Event eventSelect = eventRepository.GetByCode(code);

			if (eventSelect == null) {

				throw new Exception("code " + code + NOTFOUND);
			}

			eventBody.setId(eventSelect.getId());

			Status status = statusRepository.GetByCodeIsActive(true, eventBody.getStatus().getCode());

			if (status == null) {

				throw new Exception("status " + eventBody.getStatus().getCode() + NOTFOUND);
			}

			eventBody.setStatus(status);

			Event eventUpdate = eventRepository.save(eventBody);

			if (eventUpdate == null) {

				throw new Exception(ERRORSAVE);
			}

			// RETORN SUCESS: assignment of success return values.

			httpStatus = HttpStatus.OK;
			respCode = "1";

			result = BuilderData(eventUpdate, new ArrayList<Event>());

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
			System.out.println(msgError);

			if (msgError.contains(ERRORSAVE)) {

				httpStatus = HttpStatus.BAD_REQUEST;
				respCode = "3";
			} else if (msgError.contains(NOTFOUND)) {

				httpStatus = HttpStatus.NOT_FOUND;
				respCode = "2";

			}
			// RESPONSE: builder response API Generic

			String JSONBody = BuilderResponse(respCode, httpStatus, new ArrayList<Event>(), msgError);

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

			Event event = eventRepository.GetByCode(code);

			if (event == null) {

				throw new Exception("code " + code + NOTFOUND);
			}
			// logical exclusion

			eventRepository.InactivateByCode(false, code);

			// RETORN SUCESS: assignment of success return values.

			httpStatus = HttpStatus.OK;
			respCode = "3";

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
				respCode = "3";
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
