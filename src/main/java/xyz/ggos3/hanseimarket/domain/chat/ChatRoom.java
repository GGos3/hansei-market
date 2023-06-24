package xyz.ggos3.hanseimarket.domain.chat;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import xyz.ggos3.hanseimarket.domain.item.Item;
import xyz.ggos3.hanseimarket.domain.user.auth.AuthUser;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatRoom implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne
    @JoinColumn(name = "cutomer_id")
    private AuthUser customer;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private AuthUser store;

    @OneToMany(mappedBy = "chatRoom", cascade = CascadeType.ALL)
    private List<ChatMessage> chatMessages = new ArrayList<>();

    @CreationTimestamp
    private LocalDateTime createDate;

    @Builder
    public ChatRoom(Item item, AuthUser customer, AuthUser store) {
        this.item = item;
        this.customer = customer;
        this.store = store;
    }

    public void addChatMessages(ChatMessage chatMessage) {
        this.chatMessages.add(chatMessage);
    }
}

