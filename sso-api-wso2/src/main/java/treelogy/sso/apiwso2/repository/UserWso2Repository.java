package treelogy.sso.apiwso2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import treelogy.sso.apiwso2.model.UmUser;

@Repository
public interface UserWso2Repository extends JpaRepository<UmUser, Long> {

}
