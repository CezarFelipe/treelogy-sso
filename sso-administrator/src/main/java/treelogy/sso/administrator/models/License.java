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
@Table(name = "tb_license")
@SequenceGenerator(name="seq_license",sequenceName = "seq_license", allocationSize = 1, initialValue = 1)
public class License implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_license")
    @Column(unique = true)
    private Long id;
	
	private String descriptionShort;
	
	private String descriptionDetailed;
	
	private String productId;
	
	private Float productUnitPrice;

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

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public Float getProductUnitPrice() {
		return productUnitPrice;
	}

	public void setProductUnitPrice(Float productUnitPrice) {
		this.productUnitPrice = productUnitPrice;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
