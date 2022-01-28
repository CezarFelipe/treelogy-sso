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
@Table(name = "tb_roles")
@SequenceGenerator(name = "seq_roles", sequenceName = "seq_roles", allocationSize = 1, initialValue = 1)
public class Roles implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_roles")
	@Column(unique = true)
	private Long id;
	
	
	private String name;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
}
