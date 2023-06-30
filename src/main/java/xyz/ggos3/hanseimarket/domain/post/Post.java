package xyz.ggos3.hanseimarket.domain.post;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import xyz.ggos3.hanseimarket.domain.user.auth.AuthUser;

import java.sql.Timestamp;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30)
    private String postName;

    @Column(length = 500)
    private String postBody;

    private int view = 0;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private AuthUser authUser;

    @CreationTimestamp
    private Timestamp createDate;

    @Builder
    public Post(String postName, String postBody, int view, AuthUser user) {
        this.postName = postName;
        this.postBody = postBody;
        this.authUser = user;
    }

    public void increaseView() {
        this.view++;
    }
}
