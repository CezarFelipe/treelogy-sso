package treelogy.sso.apiwso2.model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;


@Entity
@Table(name = "um_role", uniqueConstraints = @UniqueConstraint(columnNames = "UmId", name = "um_id"))
@SequenceGenerator(name = "um_role_pk_seq", sequenceName = "um_role_pk_seq", allocationSize = 1, initialValue = 1)
public class UmRole{

	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "um_role_pk_seq")
	@Column(unique = true)
	private Long UmId;
	

	@NotNull(message = "RoleName field is required")
	@JsonProperty("rolename")
	@Column(unique = true)
	private String UmRoleName;
			
	@NotNull(message = "tenant field is required")
	@JsonProperty("tenant")
	private Integer UmTenantId;
	
	@JsonProperty("um_shared_role")
	private Boolean UmSharedRole;

	public Long getUmId() {
		return UmId;
	}

	public void setUmId(Long umId) {
		UmId = umId;
	}

	public String getUmRoleName() {
		return UmRoleName;
	}

	public void setUmRoleName(String umRoleName) {
		UmRoleName = umRoleName;
	}

	public Boolean getUmSharedRole() {
		return UmSharedRole;
	}

	public void setUmSharedRole(Boolean umSharedRole) {
		UmSharedRole = umSharedRole;
	}

	public Integer getUmTenantId() {
		return UmTenantId;
	}

	public void setUmTenantId(Integer umTenantId) {
		UmTenantId = umTenantId;
	}
	
}
