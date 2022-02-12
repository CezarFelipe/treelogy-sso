package treelogy.sso.apiwso2.dto;


import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Component
@Data
public class ResponseDTO<E> {
		
	private ArrayList<E> result;
	
	private Integer resul_count;
	
	private String log_trace;

	private EventDTO event_log;

	public ArrayList<E> getResult() {
		return result;
	}

	public void setResult(ArrayList<E> result) {
		this.result = result;
	}

	public Integer getResul_count(ArrayList<E> result) {
		
		return result.size();
	}

	public void setResul_count(Integer resul_count) {
		this.resul_count = resul_count;
	}

	public String getLog_trace() {
		return log_trace;
	}

	public void setLog_trace(String log_trace) {
		this.log_trace = log_trace;
	}

	public EventDTO getEvent_log() {
		return event_log;
	}

	public void setEvent_log(EventDTO event_log) {
		this.event_log = event_log;
	}
	
}
