package treelogy.sso.apiwso2.util;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.google.gson.Gson;

import treelogy.sso.apiwso2.dto.EventDTO;
import treelogy.sso.apiwso2.dto.ResponseDTO;
import treelogy.sso.apiwso2.dto.StatusDTO;
import treelogy.sso.apiwso2.model.Event;
import treelogy.sso.apiwso2.repository.EventRepository;

public class ResponseBuilder<E> {

	@Autowired
	private EventRepository eventRepository;

	@Autowired
	private ResponseDTO<?> resultDTO;

	@Autowired
	private Gson json;

	@SuppressWarnings("hiding")
	public <E> String BuilderResponse(String code, HttpStatus httpStatus, ArrayList<?> result, String msgError) {

		try {

			if (code.isEmpty()) {
				throw new Exception("the code field cannot be empty, check!");
			}

			EventDTO eventDTO = new EventDTO();
			StatusDTO statusDTO = new StatusDTO();

			Event event = eventRepository.GetByCodeActive(true, code);

			if (event == null) {

				// RESPONSE DEFAULT

				// event
				eventDTO.setCode("1");
				eventDTO.setDescription("standard event");
				eventDTO.setIs_active(true);

			} else {

				// status
				statusDTO.setCode(event.getStatus().getCode());
				statusDTO.setDescription(event.getStatus().getDescription());

				// event
				eventDTO.setCode(event.getCode());
				eventDTO.setDescription(event.getDescription());

				eventDTO.setStatus(statusDTO);

			}

			// result

			resultDTO.setEvent_log(eventDTO);
			resultDTO.setLog_trace(msgError);
			resultDTO.setResult(result);
			resultDTO.setResul_count(resultDTO.getResul_count(result));

			// convert to json
			String JSONBody = json.toJson(resultDTO);

			return JSONBody;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

			return null;
		}

	}
}
