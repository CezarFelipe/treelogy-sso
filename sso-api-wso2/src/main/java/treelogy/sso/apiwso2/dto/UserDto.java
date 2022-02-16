package treelogy.sso.apiwso2.dto;

import java.sql.Timestamp;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

@Component
public class UserDto {
	
	@NotNull(message = "code field is required")
	private String code;
	
	@NotNull(message = "username field is required")
	private String username;
	
	@NotNull(message = "password field is required")
	private String password;
	
	@NotNull(message = "changed_time field is required")
	private Timestamp changed_time;
	
	private Boolean require_change;

	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public Timestamp getChanged_time() {
		return changed_time;
	}

	public void setChanged_time(Timestamp changed_time) {
		this.changed_time = changed_time;
	}
	
	
}
