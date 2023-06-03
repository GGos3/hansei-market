package xyz.ggos3.hanseimarket.domain.user.login;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginUserRepository extends JpaRepository<LoginUser, Long> {
}
