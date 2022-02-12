package treelogy.sso.apiwso2.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tb_event", uniqueConstraints = @UniqueConstraint(columnNames = "id", name = "event_id"))
@SequenceGenerator(name = "seq_event", sequenceName = "seq_event", allocationSize = 1, initialValue = 1)
public class Event extends Generic {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_event")
	@Column(unique = true)
	private Long id;

	@NotNull
	@Column(unique = true, name = "code", length = 200)
	private String code;

	@NotNull
	@Column(length = 500, name = "description")
	private String description;

	@ManyToOne(optional = false)
	private Status status;
	
	@Column(length = 4, name = "isActive")
	private Boolean active;

	
	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}
