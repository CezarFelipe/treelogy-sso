package treelogy.sso.apiwso2.util;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import treelogy.sso.apiwso2.dto.ResultDTO;
import treelogy.sso.apiwso2.model.MessageModel;
import treelogy.sso.apiwso2.repository.MessageRepository;

@Component
public class ResponseConstructor<T> {

	@Autowired
	private ResultDTO<T> resultDTO;
	
	@Autowired
	private MessageRepository messageRepository;
	
	@Autowired
	private Gson JSONBody;
	
	@Autowired
	private Gson json;
	
	public Gson BuilderMessage(String code, HttpStatus httpStatus) {
		
				
		try {
			
			if (code.isEmpty()) {
				throw new Exception("the code field cannot be empty, check!");
			}
			
			MessageModel messageModel = messageRepository.FindMessageByCode(code);
			
			if (messageModel == null) {
				throw new Exception("return message not found.!");
			}
			resultDTO.setCode(messageModel.getCode());
			resultDTO.setDescription(messageModel.getDescription());
			resultDTO.setHttpStatus(httpStatus);
			resultDTO.setResulCount(resultDTO.getResulCount());
			//resultDTO.setType(messageModel.getTypeMessage());
			
			String JSONBody = json.toJson(resultDTO);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return JSONBody;
		
	}
}
