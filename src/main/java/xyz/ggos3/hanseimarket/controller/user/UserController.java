package xyz.ggos3.hanseimarket.controller.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import xyz.ggos3.hanseimarket.dto.ApiResponse;
import xyz.ggos3.hanseimarket.dto.user.request.UserInfoRequest;
import xyz.ggos3.hanseimarket.security.annotation.UserAuthorize;
import xyz.ggos3.hanseimarket.service.user.UserService;

@Slf4j
@UserAuthorize
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    @PostMapping
    public ApiResponse showUserInfo(@RequestBody UserInfoRequest request) {
        return ApiResponse.success(userService.findUserInfo(request.userId()));
    }
}

