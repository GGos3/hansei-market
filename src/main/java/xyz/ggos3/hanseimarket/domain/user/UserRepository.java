package xyz.ggos3.hanseimarket.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByStudentCode(String studentCode);
}
