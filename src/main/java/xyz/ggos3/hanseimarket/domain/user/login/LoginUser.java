package xyz.ggos3.hanseimarket.domain.user.login;

import jakarta.persistence.*;
import xyz.ggos3.hanseimarket.domain.user.User;
import xyz.ggos3.hanseimarket.domain.user.UserStatus;

import java.util.UUID;

@Entity
public class LoginUser {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String loginId;

    private String password;

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
