package treelogy.sso.apiwso2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import treelogy.sso.apiwso2.model.MessageModel;



public interface MessageRepository extends JpaRepository<MessageModel, Long> {


	@Query("SELECT u FROM MessageModel u WHERE u.code = ?1")
	MessageModel FindMessageByCode(String code);
}
