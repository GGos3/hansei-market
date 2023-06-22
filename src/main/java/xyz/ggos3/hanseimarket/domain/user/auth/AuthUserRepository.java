package xyz.ggos3.hanseimarket.domain.user.auth;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AuthUserRepository extends JpaRepository<AuthUser, Long> {
    Optional<AuthUser> findByUserId(String loginId);

    Optional<AuthUser> findByUuid(UUID uuid);
}

