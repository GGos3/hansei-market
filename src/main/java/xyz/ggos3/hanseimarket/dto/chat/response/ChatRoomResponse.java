package xyz.ggos3.hanseimarket.dto.chat.response;

import lombok.Builder;
import xyz.ggos3.hanseimarket.domain.chat.ChatRoom;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Builder
public record ChatRoomResponse(
    UUID roomId,
    Long itemId,
    String customerId,
    String storeId,
    List<ChatMessageResponse> messages,
    LocalDateTime createDate
) {
    public ChatRoomResponse(ChatRoom chatRoom, List<ChatMessageResponse> messages) {
        this(
                chatRoom.getId(),
                chatRoom.getItem().getId(),
                chatRoom.getCustomer().getUserId(),
                chatRoom.getStore().getUserId(),
                messages,
                chatRoom.getCreateDate()
        );
    }
}
