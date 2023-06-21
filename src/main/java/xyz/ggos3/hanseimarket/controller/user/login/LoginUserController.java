package xyz.ggos3.hanseimarket.controller.user.login;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import xyz.ggos3.hanseimarket.dto.user.login.request.SignInRequest;
import xyz.ggos3.hanseimarket.dto.user.login.response.SignInResponse;
import xyz.ggos3.hanseimarket.service.user.login.LoginUserService;


@RestController
@RequiredArgsConstructor
public class LoginUserController {
    private final LoginUserService loginUserService;

    @PostMapping("/login")
    public SignInResponse signIn(@RequestBody SignInRequest request) {
        return loginUserService.signIn(request);
    }
}
