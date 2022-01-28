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
@Table(name = "tb_economic_sector")
@SequenceGenerator(name = "seq_econ_sect", sequenceName = "seq_econ_sect", allocationSize = 1, initialValue = 1)
public class EconomicSector implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_econ_sect")
	@Column(unique = true)
	private Long id;

	private String descriptionShort;

	private String descriptionDetailed;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescriptionShort() {
		return descriptionShort;
	}

	public void setDescriptionShort(String descriptionShort) {
		this.descriptionShort = descriptionShort;
	}

	public String getDescriptionDetailed() {
		return descriptionDetailed;
	}

	public void setDescriptionDetailed(String descriptionDetailed) {
		this.descriptionDetailed = descriptionDetailed;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
}
