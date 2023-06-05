package xyz.ggos3.hanseimarket.domain.item;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {
    Optional<Item> findById(long id);
    Optional<Item> findByItemName(String itemName);
}
