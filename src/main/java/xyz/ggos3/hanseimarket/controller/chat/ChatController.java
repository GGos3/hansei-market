package xyz.ggos3.hanseimarket.controller.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RestController;
import xyz.ggos3.hanseimarket.domain.chat.ChatMessage;
import xyz.ggos3.hanseimarket.domain.chat.ChatRoom;
import xyz.ggos3.hanseimarket.domain.chat.MessageType;
import xyz.ggos3.hanseimarket.domain.user.auth.AuthUser;
import xyz.ggos3.hanseimarket.dto.chat.reqeuest.ChatMessageRequest;
import xyz.ggos3.hanseimarket.security.annotation.UserAuthorize;
import xyz.ggos3.hanseimarket.service.chat.ChatService;
import xyz.ggos3.hanseimarket.service.chat.RedisPublisher;
import xyz.ggos3.hanseimarket.service.user.auth.AuthUserService;

@UserAuthorize
@RestController
@RequiredArgsConstructor
public class ChatController {

    private final AuthUserService authUserService;
    private final ChatService chatService;
    private final RedisPublisher redisPublisher;

    @MessageMapping("/chat/message")
    public void message(@AuthenticationPrincipal User authUser, ChatMessageRequest request) {
        AuthUser user = authUserService.findByUuid(authUser.getUsername());
        ChatRoom chatRoom = chatService.findRoomByUuid(request.roomId());
        ChatMessage chatMessage = quitCheck(request, user, chatRoom);

        chatRoom.addChatMessages(chatMessage);

        redisPublisher.publish(chatService.getTopic(chatRoom.getId()), chatMessage);
    }

    private ChatMessage quitCheck(ChatMessageRequest request, AuthUser user, ChatRoom chatRoom) {
        if (request.type().equals(MessageType.QUIT)) {
            ChatMessage chatMessage = ChatMessage.builder()
                    .chatRoom(chatRoom)
                    .sender(user)
                    .message(user.getUserId() + "님이 퇴장하셨습니다")
                    .build();

            chatService.deleteById(chatRoom);

            return chatMessage;
        } else {
            return ChatMessage.builder()
                    .chatRoom(chatRoom)
                    .sender(user)
                    .message(request.message())
                    .build();
        }
    }
}
