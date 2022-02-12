package treelogy.sso.apiwso2.dto;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;


@Component
public class StatusDTO {
	
	@JsonProperty("code")
	private String code;
	
	@JsonProperty("description")
	private String description;

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

	
	
	
}
