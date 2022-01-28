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
@Table(name = "tb_company_license")
@SequenceGenerator(name = "seq_company_license", sequenceName = "seq_company_license", allocationSize = 1, initialValue = 1)
public class CompanyLicense implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_company_license")
	@Column(unique = true)
	private Long id;
	
	private Integer amountOfUser;
	
	private Float licenseValue;
	
	private License licenseId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getAmountOfUser() {
		return amountOfUser;
	}

	public void setAmountOfUser(Integer amountOfUser) {
		this.amountOfUser = amountOfUser;
	}

	public Float getLicenseValue() {
		return licenseValue;
	}

	public void setLicenseValue(Float licenseValue) {
		this.licenseValue = licenseValue;
	}

	public License getLicenseId() {
		return licenseId;
	}

	public void setLicenseId(License licenseId) {
		this.licenseId = licenseId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
