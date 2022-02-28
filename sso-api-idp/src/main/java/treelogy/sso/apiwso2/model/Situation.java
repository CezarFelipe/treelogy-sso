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
@Table(name = "tb_situation", uniqueConstraints = @UniqueConstraint(columnNames = "id", name = "situation_id"))
@SequenceGenerator(name = "seq_situation", sequenceName = "seq_situation", allocationSize = 1, initialValue = 1)
public class Situation extends Generic {


	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_situation")
	@Column(unique = true)
	private long id;

	@NotNull
	@Column(unique = true, name = "description", length = 200)
	private String description;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
