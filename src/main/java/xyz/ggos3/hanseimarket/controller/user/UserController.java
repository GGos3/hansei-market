package xyz.ggos3.hanseimarket.controller.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.websocket.OnOpen;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import xyz.ggos3.hanseimarket.dto.ApiResponse;
import xyz.ggos3.hanseimarket.security.annotation.UserAuthorize;
import xyz.ggos3.hanseimarket.service.item.like.LikedItemService;
import xyz.ggos3.hanseimarket.service.user.UserService;

@Tag(name = "회원관련 API")
@Slf4j
@RestController
@RequiredArgsConstructor
@UserAuthorize
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final LikedItemService likedItemService;

    @Operation(summary = "유저의 정보")
    @PostMapping("/info")
    public ApiResponse showUserInfo(@AuthenticationPrincipal User user) {
        return ApiResponse.success(userService.findUserInfo(user.getUsername()));
    }

    @Operation(summary = "유저의 찜 리스트")
    @PostMapping("/liked-item")
    public ApiResponse showUserLikedItem(@AuthenticationPrincipal User user) {
        return ApiResponse.success(likedItemService.findLikedItemsByUserId(user.getUsername()));
    }
}