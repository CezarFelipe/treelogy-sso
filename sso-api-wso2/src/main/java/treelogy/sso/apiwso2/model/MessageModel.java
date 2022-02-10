package treelogy.sso.apiwso2.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Type;

@Entity
@Table(name="tb_message")
public class MessageModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(unique = true, name="code",length=200 )
	private String code;
	private String description;
	
	@Column(name="type",length=100)
    @Type(type = "treelogy.sso.apiwso2.enumtype.EnumType",
          parameters = { @org.hibernate.annotations.Parameter(name = "enumClassName",
          value = "treelogy.sso.apiwso2.enumtype.TypeMsgEnum")})
	private String type;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
	
}
