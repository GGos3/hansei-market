package xyz.ggos3.hanseimarket.domain.chat.dto.response;

import xyz.ggos3.hanseimarket.domain.chat.ChatMessage;

import java.time.LocalDateTime;
import java.util.UUID;

public record ChatMessageResponse(
        Long id,
        UUID roomId,
        UUID senderId,
        String message,
        LocalDateTime createDate
) {
    public ChatMessageResponse(ChatMessage message) {
        this(
                message.getId(),
                message.getChatRoom().getId(),
                message.getSender().getUuid(),
                message.getMessage(),
                message.getCreateDate()
        );
    }
}
