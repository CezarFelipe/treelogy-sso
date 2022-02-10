package treelogy.sso.apiwso2.dto;


import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class ResultDTO<E> {
	
	private String type;
	private String code;
	private String description;
	private HttpStatus httpStatus;
	private ArrayList<E> result;
	private Integer resulCount;
	private String tracemessage;

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
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
