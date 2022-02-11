package treelogy.sso.apiwso2.controller;

import java.sql.Timestamp;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.google.gson.Gson;

import javassist.bytecode.Descriptor.Iterator;
import treelogy.sso.apiwso2.dto.ResultDTO;
import treelogy.sso.apiwso2.model.MessageModel;
import treelogy.sso.apiwso2.repository.MessageRepository;
import treelogy.sso.apiwso2.util.FormatDateTime;
import treelogy.sso.apiwso2.util.ResponseConstructor;

public class GenericController<E> {

	@Autowired
	private FormatDateTime formatDateTime;

	@SuppressWarnings("unused")
	@Autowired
	private ResponseConstructor<Object> responseConstructor;

	@Autowired
	private ResultDTO<E> resultDTO;

	@Autowired
	private MessageRepository messageRepository;

	private String JSONBody;

	@Autowired
	private Gson json;

	protected ArrayList<E> result;

	protected HttpStatus httpStatus;

	protected String respCode;

	protected String msgError;

	public Timestamp GetTimeStampNow() {

		String strDateTime = formatDateTime.nowDateTime();
		Timestamp stampDateTime = formatDateTime.timeStamp(strDateTime);

		return stampDateTime;
	}

	public String ResponseBuilder(String code, HttpStatus httpStatus, ArrayList<E> result, String msgError) {

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
			resultDTO.setResult(result);
			resultDTO.setResulCount(resultDTO.getResulCount());
			//resultDTO.setType(messageModel.getTypeMessage());
			resultDTO.setTracemessage(msgError);

			JSONBody = json.toJson(resultDTO);


		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return JSONBody;

	}

	public ArrayList<E> BuilderData(E obj, ArrayList<E> list) {
		
		list.add(obj);
		
		return list;
		
	}
}
