package treelogy.sso.apiwso2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import treelogy.sso.apiwso2.model.Event;



public interface EventRepository extends JpaRepository<Event, Long> {


	@Query("SELECT u FROM Event u WHERE u.code = ?1")
	Event GetByCode(String code);
	
	@Query("SELECT u FROM Event u WHERE u.active = ?1 and u.code = ?2")
	Event GetByCodeActive(Boolean active, String code);
		
}
