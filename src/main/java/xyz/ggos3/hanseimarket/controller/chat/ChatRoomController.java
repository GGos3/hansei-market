package xyz.ggos3.hanseimarket.controller.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import xyz.ggos3.hanseimarket.dto.ApiResponse;
import xyz.ggos3.hanseimarket.dto.chat.reqeuest.CreateRoomRequest;
import xyz.ggos3.hanseimarket.security.annotation.UserAuthorize;
import xyz.ggos3.hanseimarket.service.chat.ChatService;

import java.util.UUID;

@UserAuthorize
@RestController
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatRoomController {

    private final ChatService chatService;

    @GetMapping("/rooms")
    public ApiResponse rooms() {
        return ApiResponse.success(chatService.findAllRoom());
    }

    @PutMapping("/room")
    public ApiResponse createRoom(@AuthenticationPrincipal User user, @RequestBody CreateRoomRequest request) {
        return ApiResponse.success(chatService.createChatRoom(user.getUsername(), request));
    }

    @GetMapping("/room/{roomId}")
    public ApiResponse getRoomInfo(@AuthenticationPrincipal User user, @PathVariable UUID roomId) {
        return ApiResponse.success(chatService.getRoomInfo(roomId));
    }

    @PostMapping("/room/{roomId}")
    public ApiResponse getRoomMessages(@AuthenticationPrincipal User user, @PathVariable UUID roomId) {
        chatService.enterChatRoom(roomId);
        return ApiResponse.success("채팅방에 연결되었습니다.", roomId);
    }
}
