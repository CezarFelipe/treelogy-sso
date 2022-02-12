package treelogy.sso.apiwso2.controller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.google.gson.Gson;


import treelogy.sso.apiwso2.dto.EventDTO;
import treelogy.sso.apiwso2.dto.ResponseDTO;
import treelogy.sso.apiwso2.dto.StatusDTO;
import treelogy.sso.apiwso2.model.Event;
import treelogy.sso.apiwso2.repository.EventRepository;


public class GenericController<E> {

	
	@Autowired
	private ResponseDTO<E> resultDTO;
	
	@Autowired
	private EventDTO eventDTO;
	
	@Autowired
	private StatusDTO statusDTO;

	@Autowired
	private EventRepository eventRepository;

	private String JSONBody;

	@Autowired
	private Gson json;

	protected ArrayList<E> result;

	protected HttpStatus httpStatus;

	protected String respCode;

	protected String msgError;

	public Timestamp GetTimeStampNow() throws ParseException {

		SimpleDateFormat date = new SimpleDateFormat("yyyy.MM.dd.HH:mm:ss");
		
		String timeStamp = date.format(new Date());
		System.out.println("Current Time Stamp: " + timeStamp);
		
		Date parsedDate = date.parse(timeStamp);
		
	    Timestamp convTimeStamp = new java.sql.Timestamp(parsedDate.getTime());
		System.out.println("Converte Time Stamp: " + convTimeStamp);
		
		return convTimeStamp;
	}

	public String BuilderResponse(String code, HttpStatus httpStatus, ArrayList<E> result, String msgError) {

		try {

			if (code.isEmpty()) {
				throw new Exception("the code field cannot be empty, check!");
			}

			Event event = eventRepository.GetByCode(code);

			if (event == null) {

				// RESPONSE DEFAULT
				
				// event
				eventDTO.setCode("1");
				eventDTO.setDescription("standard event");
				


			} else {
				
				
				//status
				statusDTO.setCode(event.getStatus().getCode());
				statusDTO.setDescription(event.getStatus().getDescription());
				
				//event
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

	public ArrayList<E> BuilderData(E obj, ArrayList<E> list) {

		list.add(obj);

		return list;

	}
}
