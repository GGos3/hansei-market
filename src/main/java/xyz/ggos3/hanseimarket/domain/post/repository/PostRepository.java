package xyz.ggos3.hanseimarket.domain.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.ggos3.hanseimarket.domain.post.Post;
import xyz.ggos3.hanseimarket.domain.user.auth.AuthUser;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findByAuthUser(AuthUser authUser);
}
