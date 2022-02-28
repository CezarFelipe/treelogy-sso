package treelogy.sso.apiwso2.model;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

@Component
@Entity
@Table(name = "um_user_role", uniqueConstraints = @UniqueConstraint(columnNames = "UmId", name = "um_id"))
@SequenceGenerator(name = "um_user_role_pk_seq", sequenceName = "um_user_role_pk_seq", allocationSize = 1, initialValue = 1)
public class UmUserRole {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "um_user_role_pk_seq")
	@Column(unique = true)
	private Long umId;

	@NotNull(message = "um_user_id field is required")
	@ManyToOne
 	@JoinColumn(name = "um_user_id", insertable = true, updatable = true, referencedColumnName = "UmId",unique = true)
    private UmUser umUser;
	
	@NotNull(message = "um_role_id field is required")
	@ManyToOne
	@JoinColumn(name = "um_role_id", insertable = true, updatable = true, referencedColumnName = "UmId",unique = false)
	private UmRole umRole;

	@JsonProperty("is_read")
	private Boolean IsRead;
	
	
	@ManyToOne
	@JsonProperty("situation")
	private Situation situation;
	
	
	public Situation getSituation() {
		return situation;
	}

	public void setSituation(Situation situation) {
		this.situation = situation;
	}

	public Boolean getIsRead() {
		return IsRead;
	}

	public void setIsRead(Boolean isRead) {
		IsRead = isRead;
	}

	public Long getUmId() {
		return umId;
	}

	public void setUmId(Long umId) {
		this.umId = umId;
	}

	public UmUser getUmUser() {
		return umUser;
	}

	public void setUmUser(UmUser umUser) {
		this.umUser = umUser;
	}

	public UmRole getUmRole() {
		return umRole;
	}

	public void setUmRole(UmRole umRole) {
		this.umRole = umRole;
	}
	
	
	

}
