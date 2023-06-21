package xyz.ggos3.hanseimarket.domain.user.login;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoginUserRepository extends JpaRepository<LoginUser, Long> {
    Optional<LoginUser> findByUserId(String loginId);
}

