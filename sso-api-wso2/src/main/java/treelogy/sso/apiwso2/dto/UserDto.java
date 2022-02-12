package treelogy.sso.apiwso2.dto;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

@Component
public class UserDto {
	
	@NotNull(message = "id field is required")
	private String id;
	
	@NotNull(message = "username field is required")
	private String username;
	
	@NotNull(message = "password field is required")
	private String password;
	
	private Boolean require_change;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getRequire_change() {
		return require_change;
	}

	public void setRequire_change(Boolean require_change) {
		this.require_change = require_change;
	}
	
	
}
