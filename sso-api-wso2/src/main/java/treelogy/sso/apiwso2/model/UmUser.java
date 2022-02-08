package treelogy.sso.apiwso2.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@Entity
@Table(name = "um_user")
public class UmUser implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "um_user_pk_seq")
	@Column(unique = true)
	private Long UmId;
	
	@JsonProperty("id")
	private String UmUserId;
	
	@NotNull(message = "Username field is required")
	@JsonProperty("username")
	private String UmUserName;
	
	@NotNull(message = "Password field is required")
	@JsonProperty("password")
	private String UmUserPassword;
	
	private String UmSaltValue;
	

	@JsonProperty("change")
	private Boolean UmRequireChange;
	
	private Timestamp UmChangedTime;
	
	@NotNull(message = "tenant field is required")
	@JsonProperty("tenant")
	private Long UmTenantId;

	
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
	public Long getUmTenantId() {
		return UmTenantId;
	}

	public void setUmTenantId(Long umTenantId) {
		UmTenantId = umTenantId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Timestamp getUmChangedTime() {
		return UmChangedTime;
	}

	public void setUmChangedTime(Timestamp umChangedTime) {
		UmChangedTime = umChangedTime;
	}
	
	
}
