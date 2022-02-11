package treelogy.sso.apiwso2.model;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "tb_type_message", uniqueConstraints = @UniqueConstraint(columnNames = "id", name = "messagetype_id"))
@SequenceGenerator(name = "seq_type_message", sequenceName = "seq_type_message", allocationSize = 1, initialValue = 1)
public class TypeMessageModel extends GenericModel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_type_message")
	private long id;

	@Column(unique = true, name = "code", length = 100)
	private String code;

	@Column(unique = true, name = "description", length = 200)
	private String description;
	
	@OneToMany
	@JoinTable(name = "tb_message_type", joinColumns = {
			@JoinColumn(name = "message_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "type_message_id", referencedColumnName = "id") })
	private List<MessageModel> messageList;

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public List<MessageModel> getMessageList() {
		return messageList;
	}

	public void setMessageList(List<MessageModel> messageList) {
		this.messageList = messageList;
	}

	
}
