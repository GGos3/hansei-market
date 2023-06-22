package xyz.ggos3.hanseimarket.controller.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import xyz.ggos3.hanseimarket.dto.ApiResponse;
import xyz.ggos3.hanseimarket.security.annotation.UserAuthorize;
import xyz.ggos3.hanseimarket.service.user.UserService;

@Slf4j
@RestController
@RequiredArgsConstructor
@UserAuthorize
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    @PostMapping
    public ApiResponse showUserInfo(@AuthenticationPrincipal User user) {
        return ApiResponse.success(userService.findUserInfo(user.getUsername()));
    }
}