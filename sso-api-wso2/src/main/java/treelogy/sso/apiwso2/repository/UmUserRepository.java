package treelogy.sso.apiwso2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import treelogy.sso.apiwso2.model.UmUser;

public interface UmUserRepository extends JpaRepository<UmUser, Long> {

	@Query("SELECT u FROM UmUser u WHERE u.UmUserId = ?1")
	UmUser GetByCode(String code);

}
