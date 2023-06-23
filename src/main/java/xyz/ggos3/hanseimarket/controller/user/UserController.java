package xyz.ggos3.hanseimarket.controller.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import xyz.ggos3.hanseimarket.dto.ApiResponse;
import xyz.ggos3.hanseimarket.dto.user.response.OtherUserInfoResponse;
import xyz.ggos3.hanseimarket.dto.user.response.UserInfoResponse;
import xyz.ggos3.hanseimarket.security.annotation.UserAuthorize;
import xyz.ggos3.hanseimarket.service.item.like.LikedItemService;
import xyz.ggos3.hanseimarket.service.user.UserService;
import xyz.ggos3.hanseimarket.service.user.auth.AuthUserService;

@Tag(name = "회원관련 API")
@Slf4j
@RestController
@RequiredArgsConstructor
@UserAuthorize
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final AuthUserService authUserService;

    @Operation(summary = "유저의 정보", responses = {@io.swagger.v3.oas.annotations.responses.ApiResponse(content = @Content(schema = @Schema(implementation = UserInfoResponse.class)))})
    @PostMapping("/info")
    public ApiResponse showUserInfo(@AuthenticationPrincipal User user) {
        return ApiResponse.success(userService.findUserInfo(user.getUsername()));
    }

    @Operation(summary = "다른 유저 정보", responses = {@io.swagger.v3.oas.annotations.responses.ApiResponse(content = @Content(schema = @Schema(implementation = OtherUserInfoResponse.class)))})
    @PostMapping("/{userId}/info")
    public ApiResponse showOtherUserInfo(@PathVariable String userId) {
        return ApiResponse.success(userService.findOtherUserInfo(userId));
    }

    @Operation(summary = "유저 활성화", responses = {@io.swagger.v3.oas.annotations.responses.ApiResponse(content = @Content(schema = @Schema(implementation = ApiResponse.class)))})
    @PatchMapping
    public ApiResponse enableUser(@AuthenticationPrincipal User user) {
        String userId = authUserService.findByUuid(user.getUsername()).getUserId();
        userService.enableUser(userId);

        return ApiResponse.success("유저가 활성화 되었습니다.", userId);
    }

    @Operation(summary = "유저 비활성화", responses = {@io.swagger.v3.oas.annotations.responses.ApiResponse(content = @Content(schema = @Schema(implementation = ApiResponse.class)))})
    @DeleteMapping
    public ApiResponse disableUser(@AuthenticationPrincipal User user) {
        String userId = authUserService.findByUuid(user.getUsername()).getUserId();
        userService.disableUser(userId);

        return ApiResponse.success("유저가 비활성화 되었습니다.", userId);
    }
}