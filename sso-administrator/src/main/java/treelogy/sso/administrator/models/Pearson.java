package treelogy.sso.administrator.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;

@Entity
@Table(name = "tb_pearson")
@SequenceGenerator(name = "seq_pearson", sequenceName = "seq_pearson", allocationSize = 1, initialValue = 1)
public class Pearson implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String firtName;
	private String lastName;
	private String cpf;
	
	public String getFirtName() {
		return firtName;
	}
	public void setFirtName(String firtName) {
		this.firtName = firtName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
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
	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(
		name = "pearson_email", 
		uniqueConstraints = @UniqueConstraint(
				columnNames = {"pearson_id", "email_id"}, 
				name = "unique_pearson_email"),
				joinColumns = @JoinColumn(
						name = "pearson_id", 
						referencedColumnName = "id", 
						table = "pearson", 
						unique = false,
						foreignKey = @ForeignKey(
								name = "pearson_fk", 
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
		name = "pearson_addresses", 
		uniqueConstraints = @UniqueConstraint(
				columnNames = {"pearson_id", "address_id"}, 
				name = "pearson_addresses"),
				joinColumns = @JoinColumn(
						name = "pearson_id", 
						referencedColumnName = "id", 
						table = "pearson", 
						unique = false,
						foreignKey = @ForeignKey(
								name = "pearson_fk", 
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
		name = "pearson_phone", 
		uniqueConstraints = @UniqueConstraint(
				columnNames = {"pearson_id", "phone_id"}, 
				name = "unique_pearson_phone"),
				joinColumns = @JoinColumn(
						name = "company_id", 
						referencedColumnName = "id", 
						table = "pearson", 
						unique = false,
						foreignKey = @ForeignKey(
								name = "pearson_fk", 
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

}
