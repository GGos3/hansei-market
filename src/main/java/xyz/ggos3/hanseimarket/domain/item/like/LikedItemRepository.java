package xyz.ggos3.hanseimarket.domain.item.like;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.ggos3.hanseimarket.domain.item.domain.Item;
import xyz.ggos3.hanseimarket.domain.user.domain.User;

import java.util.List;
import java.util.Optional;

public interface LikedItemRepository extends JpaRepository<LikedItem, Long> {
    List<LikedItem> findByUser(User user);

    Optional<LikedItem> findByItem(Item item);
}
