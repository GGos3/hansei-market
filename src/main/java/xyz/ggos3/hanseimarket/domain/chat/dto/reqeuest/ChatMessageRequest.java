package xyz.ggos3.hanseimarket.domain.chat.dto.reqeuest;

import xyz.ggos3.hanseimarket.domain.chat.MessageType;

import java.util.UUID;

public record ChatMessageRequest(
        MessageType type,
        UUID roomId,
        String message
) {
}
