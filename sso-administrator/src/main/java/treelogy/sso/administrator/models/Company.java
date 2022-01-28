package treelogy.sso.administrator.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.ConstraintMode;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;


@Entity
@Table(name = "tb_company")
@SequenceGenerator(name = "seq_company", sequenceName = "seq_company", allocationSize = 1, initialValue = 1)
public class Company implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_company")
	@Column(unique = true)
	private Long id;
	
	private String nameSocial;
	
	private String nameFantasy;
	
	
	private String cnpj;
	
	private Boolean isentoIe;
	
	private String ie;
	
	private User owner;
	
	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(
		name = "company_email", 
		uniqueConstraints = @UniqueConstraint(
				columnNames = {"company_id", "email_id"}, 
				name = "unique_company_email"),
				joinColumns = @JoinColumn(
						name = "company_id", 
						referencedColumnName = "id", 
						table = "company", 
						unique = false,
						foreignKey = @ForeignKey(
								name = "company_fk", 
								value = ConstraintMode.CONSTRAINT)),
				inverseJoinColumns = @JoinColumn(
						name = "email_id", 
						referencedColumnName = "id",
						table = "email",
						unique = false,
						updatable = false,
						foreignKey = @ForeignKey(
								name = "email_fk",
								value = ConstraintMode.CONSTRAINT))
	)
	private List<Email> emails = new ArrayList<>();
	
	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(
		name = "company_addresses", 
		uniqueConstraints = @UniqueConstraint(
				columnNames = {"company_id", "address_id"}, 
				name = "company_addresses"),
				joinColumns = @JoinColumn(
						name = "company_id", 
						referencedColumnName = "id", 
						table = "company", 
						unique = false,
						foreignKey = @ForeignKey(
								name = "company_fk", 
								value = ConstraintMode.CONSTRAINT)),
				inverseJoinColumns = @JoinColumn(
						name = "address_id", 
						referencedColumnName = "id",
						table = "address",
						unique = false,
						updatable = false,
						foreignKey = @ForeignKey(
								name = "address_fk",
								value = ConstraintMode.CONSTRAINT))
	)

	private List<Address> addresses = new ArrayList<>();
	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(
		name = "company_phone", 
		uniqueConstraints = @UniqueConstraint(
				columnNames = {"company_id", "phone_id"}, 
				name = "unique_company_phone"),
				joinColumns = @JoinColumn(
						name = "company_id", 
						referencedColumnName = "id", 
						table = "company", 
						unique = false,
						foreignKey = @ForeignKey(
								name = "company_fk", 
								value = ConstraintMode.CONSTRAINT)),
				inverseJoinColumns = @JoinColumn(
						name = "phone_id", 
						referencedColumnName = "id",
						table = "phone",
						unique = false,
						updatable = false,
						foreignKey = @ForeignKey(
								name = "phone_fk",
								value = ConstraintMode.CONSTRAINT))
	)
	private List<Phone> phones = new ArrayList<>();
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNameSocial() {
		return nameSocial;
	}

	public void setNameSocial(String nameSocial) {
		this.nameSocial = nameSocial;
	}

	public String getNameFantasy() {
		return nameFantasy;
	}

	public void setNameFantasy(String nameFantasy) {
		this.nameFantasy = nameFantasy;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public Boolean getIsentoIe() {
		return isentoIe;
	}

	public void setIsentoIe(Boolean isentoIe) {
		this.isentoIe = isentoIe;
	}

	public String getIe() {
		return ie;
	}

	public void setIe(String ie) {
		this.ie = ie;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public List<Email> getEmails() {
		return emails;
	}

	public void setEmails(List<Email> emails) {
		this.emails = emails;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public List<Phone> getPhones() {
		return phones;
	}

	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}	
	
}
