package xyz.ggos3.hanseimarket.domain.post.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import xyz.ggos3.hanseimarket.domain.post.domain.Post;
import xyz.ggos3.hanseimarket.domain.user.auth.domain.AuthUser;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findByAuthUser(AuthUser authUser);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Post p SET p.postName = :postName WHERE p.id = :id")
    void updatePostName(Long id, String postName);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Post p SET p.postBody = :postBody WHERE p.id = :id")
    void updatePostBody(Long id, String postBody);
}
