package xyz.ggos3.hanseimarket.domain.user.auth.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.ggos3.hanseimarket.domain.user.auth.domain.AuthUser;

import java.util.Optional;
import java.util.UUID;

public interface AuthUserRepository extends JpaRepository<AuthUser, Long> {
    Optional<AuthUser> findByUserId(String loginId);

    Optional<AuthUser> findByUuid(UUID uuid);
}

