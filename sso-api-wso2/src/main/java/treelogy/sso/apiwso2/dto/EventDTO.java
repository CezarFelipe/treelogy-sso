package treelogy.sso.apiwso2.dto;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

import treelogy.sso.apiwso2.model.Status;

@Component
public class EventDTO {
	
	@NotNull(message = "code field is required")
	@JsonProperty("code")
	private String code;
	
	@NotNull(message = "description field is required")
	@JsonProperty("description")
	private String description;
	
	@NotNull(message = "status field is required")
	@JsonProperty("status")
	private StatusDTO status;

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

	public StatusDTO getStatus() {
		return status;
	}

	public void setStatus(StatusDTO status) {
		this.status = status;
	}
	
	
}
