package xyz.ggos3.hanseimarket.domain.item.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import xyz.ggos3.hanseimarket.domain.user.domain.User;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {
    Optional<Item> findByItemName(String itemName);

    Optional<Item> findByUser(User user);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Item i SET i.status = :status WHERE i.id = :id")
    void updateStatus(Long id, ItemStatus status);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Item i SET i.itemName = :itemName , i.price = :price, i.description = :description WHERE i.id = :id")
    void updateItem(Long id, String itemName, int price, String description);
}
