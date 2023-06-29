package xyz.ggos3.hanseimarket.domain.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.ggos3.hanseimarket.domain.post.Comment;
import xyz.ggos3.hanseimarket.domain.post.Post;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<Comment> findByPost(Post post);
}
