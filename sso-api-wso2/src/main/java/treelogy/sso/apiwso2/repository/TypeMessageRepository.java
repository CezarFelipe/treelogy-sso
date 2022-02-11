package treelogy.sso.apiwso2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import treelogy.sso.apiwso2.model.MessageModel;
import treelogy.sso.apiwso2.model.TypeMessageModel;



public interface TypeMessageRepository extends JpaRepository<TypeMessageModel, Long> {


	@Query("SELECT u FROM TypeMessageModel u WHERE u.code = ?1")
	TypeMessageModel FindTypeMessageByCode(String code);
}
