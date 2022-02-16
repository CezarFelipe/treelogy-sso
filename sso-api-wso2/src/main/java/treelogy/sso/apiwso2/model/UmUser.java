package treelogy.sso.apiwso2.model;


import java.sql.Timestamp;

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

@Component
@Entity
@Table(name = "um_user", uniqueConstraints = @UniqueConstraint(columnNames = "UmId", name = "um_id"))
@SequenceGenerator(name = "um_user_pk_seq", sequenceName = "um_user_pk_seq", allocationSize = 1, initialValue = 1)
public class UmUser{

	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "um_user_pk_seq")
	@Column(unique = true)
	private Long UmId;
	
	private String UmUserId;
	
	@NotNull(message = "Username field is required")
	@JsonProperty("username")
	@Column(unique = true)
	private String UmUserName;
	
	@NotNull(message = "Password field is required")
	@JsonProperty("password")
	private String UmUserPassword;
	
	private String UmSaltValue;
	

	@JsonProperty("change")
	private Boolean UmRequireChange;
	
	@NotNull(message = "change-time field is required")
	private Timestamp UmChangedTime;
	
	@NotNull(message = "tenant field is required")
	@JsonProperty("tenant")
	private Integer UmTenantId;

	
	public Long getUmId() {
		return UmId;
	}

	public void setUmId(Long umId) {
		UmId = umId;
	}

	public String getUmUserId() {
		return UmUserId;
	}

	public void setUmUserId(String umUserId) {
		UmUserId = umUserId;
	}

	public String getUmUserName() {
		return UmUserName;
	}

	public void setUmUserName(String umUserName) {
		UmUserName = umUserName;
	}

	public String getUmUserPassword() {
		return UmUserPassword;
	}

	public void setUmUserPassword(String umUserPassword) {
		UmUserPassword = umUserPassword;
	}

	public String getUmSaltValue() {
		return UmSaltValue;
	}

	public void setUmSaltValue(String umSaltValue) {
		UmSaltValue = umSaltValue;
	}

	public Boolean getUmRequireChange() {
		return UmRequireChange;
	}

	public void setUmRequireChange(Boolean umRequireChange) {
		UmRequireChange = umRequireChange;
	}


	public Timestamp getUmChangedTime() {
		return UmChangedTime;
	}

	public void setUmChangedTime(Timestamp umChangedTime) {
		UmChangedTime = umChangedTime;
	}

	public Integer getUmTenantId() {
		return UmTenantId;
	}

	public void setUmTenantId(Integer umTenantId) {
		UmTenantId = umTenantId;
	}
	
	
	
}
