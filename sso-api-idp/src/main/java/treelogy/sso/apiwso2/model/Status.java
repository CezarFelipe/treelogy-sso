package treelogy.sso.apiwso2.model;

import java.util.List;

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
import javax.validation.constraints.NotNull;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "tb_status", uniqueConstraints = @UniqueConstraint(columnNames = "id", name = "status_id"))
@SequenceGenerator(name = "seq_status", sequenceName = "seq_status", allocationSize = 1, initialValue = 1)
public class Status extends Generic {


	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_status")
	@Column(unique = true)
	private long id;

	@NotNull
	@Column(unique = true, name = "code", length = 100)
	private String code;

	@NotNull
	@Column(unique = true, name = "description", length = 200)
	private String description;
	
	@NotNull
	@Column(name = "active", length = 10)
	private Boolean active;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "tb_event_status", joinColumns = {
			@JoinColumn(name = "event_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "status_id", referencedColumnName = "id") })
	private List<Event> events;

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

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}
	
	

}
