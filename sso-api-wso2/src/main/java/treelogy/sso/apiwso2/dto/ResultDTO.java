package treelogy.sso.apiwso2.dto;


import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import treelogy.sso.apiwso2.model.TypeMessageModel;

@Component
public class ResultDTO<E> {
	
	//private TypeMessageModel type;
	private String code;
	private String description;
	private HttpStatus httpStatus;
	private ArrayList<E> result;
	private Integer resulCount;
	private String tracemessage;

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}
	public Integer getResulCount() {
		return resulCount;
	}
	public void setResulCount(Integer resulCount) {
		this.resulCount = resulCount;
	}
	public ArrayList<E> getResult() {
		return result;
	}
	public void setResult(ArrayList<E> arrayList) {
		this.result = arrayList;
	}
	public String getTracemessage() {
		return tracemessage;
	}
	public void setTracemessage(String tracemessage) {
		this.tracemessage = tracemessage;
	}
	
}
