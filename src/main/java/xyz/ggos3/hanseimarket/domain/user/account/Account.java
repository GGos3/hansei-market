package xyz.ggos3.hanseimarket.domain.user.account;

import jakarta.persistence.*;
import lombok.Getter;
import xyz.ggos3.hanseimarket.domain.user.User;

import java.util.UUID;

@Entity
@Getter
public class Account {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String accountId;
    private String accountPassword;
    private boolean isAlive = true;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Account() {
    }

    public Account(String accountId, String accountPassword) {
        this.accountId = accountId;
        this.accountPassword = accountPassword;
    }

    public void disable() {
        this.isAlive = false;
    }
}
