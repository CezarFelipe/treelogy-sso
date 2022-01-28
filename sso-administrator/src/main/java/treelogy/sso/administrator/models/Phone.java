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
@Table(name = "tb_phone")
@SequenceGenerator(name = "seq_phone", sequenceName = "seq_phone", allocationSize = 1, initialValue = 1)
public class Phone implements Serializable {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_email")
	@Column(unique = true)
	private Long id;
	
	private String ddi;
	
	private String dd;
	
	private String phone;
	
	private Boolean isVerify;
	
	private String codeVerify;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDdi() {
		return ddi;
	}

	public void setDdi(String ddi) {
		this.ddi = ddi;
	}

	public String getDd() {
		return dd;
	}

	public void setDd(String dd) {
		this.dd = dd;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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
