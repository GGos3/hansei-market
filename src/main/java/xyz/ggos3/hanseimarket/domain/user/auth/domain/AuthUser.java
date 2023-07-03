package xyz.ggos3.hanseimarket.domain.user.auth.domain;

import jakarta.persistence.*;
import lombok.Getter;
import xyz.ggos3.hanseimarket.domain.user.domain.User;
import xyz.ggos3.hanseimarket.domain.user.domain.UserStatus;

import java.util.UUID;

@Entity
@Getter
public class AuthUser {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @ManyToOne
    @JoinColumn(name = "user_deteil_id")
    private User user;

    private String userId;

    private String password;

    @Enumerated(EnumType.STRING)
    private UserType userType = UserType.USER;

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    public AuthUser() {
    }

    public AuthUser(User user) {
        this.user = user;
        this.userId = user.getUserId();
        this.password = user.getUserPassword();
        this.status = user.getStatus();
    }
}
