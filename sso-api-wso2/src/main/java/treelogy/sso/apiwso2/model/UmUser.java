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

import org.springframework.stereotype.Component;

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
	private Long umid;
	
	private String umuserid;
	
	private String umusername;
	
	private String umuserpassword;
	
	private String umsaltvalue;
	
	private Boolean umrequirechange;
	
	private Timestamp umchangedtime;
	
	private Long umtenantid;

	

	public Long getUmid() {
		return umid;
	}

	public void setUmid(Long umid) {
		this.umid = umid;
	}

	public String getUmuserid() {
		return umuserid;
	}

	public void setUmuserid(String umuserid) {
		this.umuserid = umuserid;
	}

	public String getUmusername() {
		return umusername;
	}

	public void setUmusername(String umusername) {
		this.umusername = umusername;
	}

	public String getUmuserpassword() {
		return umuserpassword;
	}

	public void setUmuserpassword(String umuserpassword) {
		this.umuserpassword = umuserpassword;
	}

	public String getUmsaltvalue() {
		return umsaltvalue;
	}

	public void setUmsaltvalue(String umsaltvalue) {
		this.umsaltvalue = umsaltvalue;
	}

	public Boolean getUmrequirechange() {
		return umrequirechange;
	}

	public void setUmrequirechange(Boolean umrequirechange) {
		this.umrequirechange = umrequirechange;
	}

	public Timestamp getUmchangedtime() {
		return umchangedtime;
	}

	public void setUmchangedtime(Timestamp umchangedtime) {
		this.umchangedtime = umchangedtime;
	}

	public Long getUmtenantid() {
		return umtenantid;
	}

	public void setUmtenantid(Long umtenantid) {
		this.umtenantid = umtenantid;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
}
