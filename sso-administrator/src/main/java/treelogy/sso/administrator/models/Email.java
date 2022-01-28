package treelogy.sso.administrator.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "tb_email")
@SequenceGenerator(name = "seq_email", sequenceName = "seq_email", allocationSize = 1, initialValue = 1)
public class Email implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_email")
	@Column(unique = true)
	private Long id;
	
	private String addressMail;
	
	private Boolean isVerify;
	
	private String codeVerify;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAddressMail() {
		return addressMail;
	}

	public void setAddressMail(String addressMail) {
		this.addressMail = addressMail;
	}

	public Boolean getIsVerify() {
		return isVerify;
	}

	public void setIsVerify(Boolean isVerify) {
		this.isVerify = isVerify;
	}

	public String getCodeVerify() {
		return codeVerify;
	}

	public void setCodeVerify(String codeVerify) {
		this.codeVerify = codeVerify;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	

}
