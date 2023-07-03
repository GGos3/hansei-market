package xyz.ggos3.hanseimarket.domain.item.like;

import jakarta.persistence.*;
import lombok.Getter;
import xyz.ggos3.hanseimarket.domain.item.domain.Item;
import xyz.ggos3.hanseimarket.domain.user.domain.User;

@Entity
@Getter
public class LikedItem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    public LikedItem() {
    }

    public LikedItem(User user, Item item) {
        this.user = user;
        this.item = item;
    }
}