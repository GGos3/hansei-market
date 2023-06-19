package xyz.ggos3.hanseimarket.domain.user.login;

import jakarta.persistence.*;
import lombok.Getter;
import xyz.ggos3.hanseimarket.domain.user.User;
import xyz.ggos3.hanseimarket.domain.user.UserStatus;

import java.util.UUID;

@Entity
@Getter
public class LoginUser {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String loginId;

    private String password;

    private String role = "ROLE_USER";

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    public LoginUser() {
    }

    public LoginUser(User user) {
        this.user = user;
        this.loginId = user.getUserId();
        this.password = user.getUserPassword();
        this.status = user.getStatus();
    }
}
