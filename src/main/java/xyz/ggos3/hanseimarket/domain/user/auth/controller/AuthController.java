package xyz.ggos3.hanseimarket.domain.user.auth.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.ggos3.hanseimarket.domain.user.domain.User;
import xyz.ggos3.hanseimarket.common.dto.ApiResponse;
import xyz.ggos3.hanseimarket.domain.user.auth.dto.request.SignInRequest;
import xyz.ggos3.hanseimarket.domain.user.auth.dto.response.SignInResponse;
import xyz.ggos3.hanseimarket.domain.user.dto.request.ValidateUserIdRequest;
import xyz.ggos3.hanseimarket.domain.user.auth.dto.request.SignUpRequest;
import xyz.ggos3.hanseimarket.domain.user.service.UserService;
import xyz.ggos3.hanseimarket.domain.user.auth.service.AuthUserService;

@Tag(name = "회원 가입 및 로그인 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final AuthUserService authUserService;

    @Operation(summary = "중복 ID 검증")
    @PostMapping("/validate")
    public ApiResponse<String> validateId(@RequestBody ValidateUserIdRequest request) {
        userService.validateUser(request.userId());
        return ApiResponse.success("사용할 수 있는 ID 입니다");
    }

    @Operation(summary = "회원가입")
    @PostMapping("/sign-up")
    public ApiResponse<User> signUp(@RequestBody SignUpRequest request) {
        return ApiResponse.success(userService.createAccount(request));
    }

    @Operation(summary = "로그인")
    @PostMapping("/sign-in")
    public ApiResponse<SignInResponse> signIn(@RequestBody SignInRequest request) {
        return ApiResponse.success(authUserService.signIn(request));
    }
}
