package treelogy.sso.apiwso2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import treelogy.sso.apiwso2.model.Status;


public interface StatusRepository extends JpaRepository<Status, Long> {

	@Query("SELECT u FROM Status u WHERE u.code = ?1")
	Status GetByCode(String code);
	
	@Query("SELECT u FROM Status u WHERE u.active= ?1 and u.code = ?2")
	Status GetByCodeIsActive(Boolean active, String code);
}
