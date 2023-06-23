package xyz.ggos3.hanseimarket.controller.user.auth;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.ggos3.hanseimarket.domain.user.User;
import xyz.ggos3.hanseimarket.dto.ApiResponse;
import xyz.ggos3.hanseimarket.dto.user.auth.request.SignInRequest;
import xyz.ggos3.hanseimarket.dto.user.auth.response.SignInResponse;
import xyz.ggos3.hanseimarket.dto.user.request.ValidateUserIdRequest;
import xyz.ggos3.hanseimarket.dto.user.auth.request.SignUpRequest;
import xyz.ggos3.hanseimarket.service.user.UserService;
import xyz.ggos3.hanseimarket.service.user.auth.AuthUserService;

@Tag(name = "회원 가입 및 로그인 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final AuthUserService authUserService;

    @Operation(summary = "중복 ID 검증")
    @PostMapping("/validate")
    public ApiResponse validateId(@RequestBody ValidateUserIdRequest request) {
        userService.validateUser(request.userId());
        return ApiResponse.success("사용할 수 있는 ID 입니다");
    }

    @Operation(summary = "회원가입", responses = {@io.swagger.v3.oas.annotations.responses.ApiResponse(content = @Content(schema = @Schema(implementation = User.class)))})
    @PostMapping("/sign-up")
    public ApiResponse signUp(@RequestBody SignUpRequest request) {
        return ApiResponse.success(userService.createAccount(request));
    }

    @Operation(summary = "로그인", responses = {@io.swagger.v3.oas.annotations.responses.ApiResponse(content = @Content(schema = @Schema(implementation = SignInResponse.class)))})
    @PostMapping("/sign-in")
    public ApiResponse signIn(@RequestBody SignInRequest request) {
        return ApiResponse.success(authUserService.signIn(request));
    }
}
