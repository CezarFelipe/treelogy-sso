package treelogy.sso.apiwso2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import treelogy.sso.apiwso2.model.Event;
import treelogy.sso.apiwso2.model.Situation;
import treelogy.sso.apiwso2.model.UmRole;
import treelogy.sso.apiwso2.model.UmUser;
import treelogy.sso.apiwso2.model.UmUserRole;



public interface SituationRepository extends JpaRepository<Situation, Long> {


	@Query("SELECT u FROM Situation u WHERE u.description = ?1")
	Situation GetSituationByName(String name);
	
	@Query("SELECT u FROM UmUser u WHERE u.situation = ?1 ")
	Iterable<UmUser> GetUserBySituation(Situation situation);
	
	@Query("SELECT u FROM UmRole u WHERE u.situation = ?1 ")
	Iterable<UmRole> GetRoleBySituation(Situation situation);
	
	@Query("SELECT u FROM UmUserRole u WHERE u.situation = ?1 ")
	 Iterable<UmUserRole> GetUserRoleBySituation(Situation situation);
		
}
