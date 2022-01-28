package treelogy.sso.administrator.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "tb_product")
@SequenceGenerator(name = "seq_product", sequenceName = "seq_product", allocationSize = 1, initialValue = 1)
public class Product implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_product")
	@Column(unique = true)
	private Long id;

	private String descriptionShort;

	private String descriptionDetailed;
	
	private String url;
	
	private String logo;
	
	@ManyToOne(optional = false)
	private EconomicSector sectorId;

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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public EconomicSector getSectorId() {
		return sectorId;
	}

	public void setSectorId(EconomicSector sectorId) {
		this.sectorId = sectorId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
