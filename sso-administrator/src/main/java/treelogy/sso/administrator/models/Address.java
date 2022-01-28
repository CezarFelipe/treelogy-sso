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
@Table(name = "tb_address")
@SequenceGenerator(name = "seq_address", sequenceName = "seq_address", allocationSize = 1, initialValue = 1)
public class Address implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_address")
	@Column(unique = true)
	private Long id;
	
	private PublicPlace placeId;
	
	private String street;
	
	private String number;
	
	private String neighborhood;
	
	private String city;
	
	private String state;
	
	private String zipCode;
	
	private String codeIbge;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PublicPlace getPlaceId() {
		return placeId;
	}

	public void setPlaceId(PublicPlace placeId) {
		this.placeId = placeId;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCodeIbge() {
		return codeIbge;
	}

	public void setCodeIbge(String codeIbge) {
		this.codeIbge = codeIbge;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
}
