package xyz.ggos3.hanseimarket.domain.item;

import jakarta.persistence.*;
import xyz.ggos3.hanseimarket.domain.user.User;

import java.time.LocalDateTime;

@Entity
public class Item {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String itemName;
    private String description;
    private int like = 0;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdDate;

    public Item() {
    }

    public Item(User user, String itemName, int like) {
        this.user = user;
        this.itemName = itemName;
        this.like = like;
    }
}
