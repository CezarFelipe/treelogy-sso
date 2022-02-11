package treelogy.sso.apiwso2.model;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.JoinColumn;
import javax.persistence.ForeignKey;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "tb_message", uniqueConstraints = @UniqueConstraint(columnNames = "id", name = "message_id"))
@SequenceGenerator(name = "seq_message", sequenceName = "seq_message", allocationSize = 1, initialValue = 1)
public class MessageModel extends GenericModel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_message")
	private Long id;

	@Column(unique = true, name = "code", length = 200)
	private String code;
	private String description;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinTable(name = "tb_message_type", joinColumns = {
			@JoinColumn(name = "message_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "type_message_id", referencedColumnName = "id") })
	private TypeMessageModel typeMessage;

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

	public TypeMessageModel getTypeMessage() {
		return typeMessage;
	}

	public void setTypeMessage(TypeMessageModel typeMessage) {
		this.typeMessage = typeMessage;
	}
   
	
	
}
