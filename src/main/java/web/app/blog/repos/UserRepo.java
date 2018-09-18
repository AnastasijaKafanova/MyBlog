package web.app.blog.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import web.app.blog.domain.User;


public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
