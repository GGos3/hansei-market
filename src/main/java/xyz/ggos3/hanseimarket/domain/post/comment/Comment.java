package xyz.ggos3.hanseimarket.domain.post.comment;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import xyz.ggos3.hanseimarket.domain.post.Post;
import xyz.ggos3.hanseimarket.domain.user.auth.AuthUser;

import java.sql.Timestamp;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 200)
    private String comment;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private AuthUser authUser;
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;
    @CreationTimestamp
    private Timestamp createDate;

    public Comment(String comment, AuthUser authUser, Post post) {
        this.comment = comment;
        this.authUser = authUser;
        this.post = post;
    }
}
