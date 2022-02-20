package treelogy.sso.apiwso2.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import treelogy.sso.apiwso2.model.Situation;
import treelogy.sso.apiwso2.model.UmRole;
import treelogy.sso.apiwso2.model.UmUser;
import treelogy.sso.apiwso2.model.UmUserRole;

public interface UmUserRepository extends JpaRepository<UmUser, Long> {

	@Query("SELECT u FROM UmUser u WHERE u.UmUserId = ?1")
	UmUser GetByCode(String code);

	@Transactional
	@Modifying
	@Query(value = "insert into um_user_role (um_role_id, um_user_id, um_tenant_id) values (?1, ?2, ?3)", nativeQuery = true)
	void AssignRoleToUser (Long roleId, Long userId, Integer tenant);
	
	@Query("SELECT u FROM UmUserRole u WHERE u.umUser = ?1")
	UmUserRole GetAssignByUser(UmUser user);
	
	@Transactional
	@Modifying
	@Query("update UmUserRole u set u.umRole = ?1 where u.umUser = ?2")
	Integer AssignRoleToUserUpdate(UmRole role, UmUser user);
	
	@Query("SELECT u FROM UmUser u WHERE u.IsRead = true")
	Iterable<UmUser>GetUserByIsRead();
	
	@Query("SELECT u FROM UmUser u WHERE u.situation = ?1")
	Iterable<UmUser>GetUserBySituation(Situation situation);
	
	@Query("SELECT u FROM UmUserRole u WHERE u.IsRead = true")
	Iterable<UmUserRole>GetRoleUserByIsRead();
	
	@Transactional
	@Modifying
	@Query("update UmUserRole u set u.situation = ?1 where u.umUser = ?2")
	Integer AssignToUserUpdate(Situation situation, UmUser user);
	
}
