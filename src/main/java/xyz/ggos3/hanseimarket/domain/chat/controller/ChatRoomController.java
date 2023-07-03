package xyz.ggos3.hanseimarket.domain.chat.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import xyz.ggos3.hanseimarket.common.dto.ApiResponse;
import xyz.ggos3.hanseimarket.domain.chat.dto.reqeuest.CreateRoomRequest;
import xyz.ggos3.hanseimarket.common.security.annotation.UserAuthorize;
import xyz.ggos3.hanseimarket.domain.chat.dto.response.ChatRoomResponse;
import xyz.ggos3.hanseimarket.domain.chat.service.ChatService;

import java.util.List;
import java.util.UUID;

@UserAuthorize
@RestController
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatRoomController {

    private final ChatService chatService;

    @GetMapping("/rooms")
    public ApiResponse<List<ChatRoomResponse>> getRoomByUser(@AuthenticationPrincipal User user) {
        return ApiResponse.success(chatService.getRoomByUser(user.getUsername()));
    }

    @PutMapping("/room")
    public ApiResponse<ChatRoomResponse> createRoom(@AuthenticationPrincipal User user, @RequestBody CreateRoomRequest request) {
        return ApiResponse.success(chatService.createChatRoom(user.getUsername(), request));
    }

    @GetMapping("/room/{roomId}")
    public ApiResponse<ChatRoomResponse> getRoomInfo(@AuthenticationPrincipal User user, @PathVariable UUID roomId) {
        return ApiResponse.success(chatService.getRoomInfo(roomId));
    }

    @PostMapping("/room/{roomId}")
    public ApiResponse<UUID> getRoomMessages(@PathVariable UUID roomId) {
        return ApiResponse.success("채팅방에 연결되었습니다.", roomId);
    }
}