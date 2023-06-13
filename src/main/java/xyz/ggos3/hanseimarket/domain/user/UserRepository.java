package xyz.ggos3.hanseimarket.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserId(String userId);
    Optional<User> findByStudentCode(String studentCode);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE User u SET u.status = :status WHERE u.id = :id")
    void updateUserStatus(Long id, UserStatus status);

    void deleteUserByUserId(String userId);
}
