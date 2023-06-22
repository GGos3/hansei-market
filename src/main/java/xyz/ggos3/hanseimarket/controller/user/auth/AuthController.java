package xyz.ggos3.hanseimarket.controller.user.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.ggos3.hanseimarket.dto.ApiResponse;
import xyz.ggos3.hanseimarket.dto.user.auth.request.SignInRequest;
import xyz.ggos3.hanseimarket.dto.user.request.ValidateUserIdRequest;
import xyz.ggos3.hanseimarket.dto.user.auth.request.SignUpRequest;
import xyz.ggos3.hanseimarket.service.user.UserService;
import xyz.ggos3.hanseimarket.service.user.auth.AuthUserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;
    private final AuthUserService authUserService;

    @PostMapping("/validate")
    public ApiResponse validateId(@RequestBody ValidateUserIdRequest request) {
        userService.validateUser(request.getUserId());
        return ApiResponse.success("사용할 수 있는 ID 입니다");
    }

    @PostMapping("/sign-up")
    public ApiResponse signUp(@RequestBody SignUpRequest request) {
        return ApiResponse.success(userService.createAccount(request));
    }

    @PostMapping("/sign-in")
    public ApiResponse signIn(@RequestBody SignInRequest request) {
        return ApiResponse.success(authUserService.signIn(request));
    }
}
