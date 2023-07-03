package xyz.ggos3.hanseimarket.domain.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import xyz.ggos3.hanseimarket.common.dto.ApiResponse;
import xyz.ggos3.hanseimarket.domain.user.dto.response.OtherUserInfoResponse;
import xyz.ggos3.hanseimarket.domain.user.dto.response.UserInfoResponse;
import xyz.ggos3.hanseimarket.common.security.annotation.UserAuthorize;
import xyz.ggos3.hanseimarket.domain.user.service.UserServiceImpl;
import xyz.ggos3.hanseimarket.domain.user.auth.service.AuthUserImpl;

@Tag(name = "회원관련 API")
@Slf4j
@RestController
@RequiredArgsConstructor
@UserAuthorize
@RequestMapping("/user")
public class UserController {

    private final UserServiceImpl userService;
    private final AuthUserImpl authUserService;

    @Operation(summary = "유저의 정보")
    @PostMapping("/info")
    public ApiResponse<UserInfoResponse> showUserInfo(@AuthenticationPrincipal User user) {
        return ApiResponse.success(userService.findUserInfo(user.getUsername()));
    }

    @Operation(summary = "다른 유저 정보")
    @PostMapping("/{userId}/info")
    public ApiResponse<OtherUserInfoResponse> showOtherUserInfo(@PathVariable String userId) {
        return ApiResponse.success(userService.findOtherUserInfo(userId));
    }

    @Operation(summary = "유저 활성화")
    @PatchMapping
    public ApiResponse<String> enableUser(@AuthenticationPrincipal User user) {
        String userId = authUserService.findByUuid(user.getUsername()).getUserId();
        userService.enableUser(userId);

        return ApiResponse.success("유저가 활성화 되었습니다.", userId);
    }

    @Operation(summary = "유저 비활성화")
    @DeleteMapping
    public ApiResponse<String> disableUser(@AuthenticationPrincipal User user) {
        String userId = authUserService.findByUuid(user.getUsername()).getUserId();
        userService.disableUser(userId);

        return ApiResponse.success("유저가 비활성화 되었습니다.", userId);
    }
}