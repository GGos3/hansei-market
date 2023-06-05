package xyz.ggos3.hanseimarket.domain.item;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.ggos3.hanseimarket.domain.user.User;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {
    Optional<Item> findById(long id);
    Optional<Item> findByItemName(String itemName);

    Optional<Item> findByUser(User user);
}
