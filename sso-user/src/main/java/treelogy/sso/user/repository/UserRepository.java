package treelogy.sso.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import treelogy.sso.user.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);
}
