package xyz.ggos3.hanseimarket.domain.post.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import xyz.ggos3.hanseimarket.domain.post.Post;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<Comment> findByPost(Post post);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Comment c SET c.comment = :comment WHERE c.id = :id")
    void updateComment(Long id, String comment);
}
