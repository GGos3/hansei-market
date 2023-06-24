package xyz.ggos3.hanseimarket.domain.chat;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import xyz.ggos3.hanseimarket.domain.user.auth.AuthUser;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatMessage {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private MessageType type;

    @Column(length = 500)
    private String message;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private ChatRoom chatRoom;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private AuthUser sender;

    @CreationTimestamp
    private LocalDateTime createDate;

    @Builder
    public ChatMessage(MessageType type, String message, ChatRoom chatRoom, AuthUser sender) {
        this.type = type;
        this.message = message;
        this.chatRoom = chatRoom;
        this.sender = sender;
    }
}
