package treelogy.sso.apiwso2.dto;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

@Component
public class AssignDto {
	
	@NotNull(message = "userCode field is required")
	private String userCode;
	
	@NotNull(message = "roleCode field is required")
	private Long roleCode;

	
	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public Long getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(Long roleCode) {
		this.roleCode = roleCode;
	}
	
	
	
	
}
