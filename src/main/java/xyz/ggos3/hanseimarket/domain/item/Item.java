package xyz.ggos3.hanseimarket.domain.item;

import jakarta.persistence.*;
import lombok.Getter;
import xyz.ggos3.hanseimarket.domain.user.User;

import java.time.LocalDateTime;

@Entity
@Getter
public class Item {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String itemName;
    private String description;
    private int likeCount = 0;
    private int price = 0;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdDate;

    public Item() {
    }

    public Item(User user, String itemName, int price, String description) {
        this.user = user;
        this.itemName = itemName;
        this.price = price;
        this.description = description;
    }

    public void sumLike() {
        this.likeCount =+ 1;
    }
}
