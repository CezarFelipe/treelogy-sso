package treelogy.sso.apiwso2.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import treelogy.sso.apiwso2.model.UmRole;
import treelogy.sso.apiwso2.model.UmUser;

public interface UmRoleRepository extends JpaRepository<UmRole, Long> {

	@Query("SELECT u FROM UmRole u WHERE u.UmId = ?1")
	UmRole GetByCode(Long code);
	
	
	@Query("SELECT u FROM UmRole u WHERE u.IsRead = true")
	Iterable<UmRole>GetRoleByIsRead();

}
