package xyz.ggos3.hanseimarket.controller.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import xyz.ggos3.hanseimarket.dto.user.account.request.AccountCreateRequest;
import xyz.ggos3.hanseimarket.service.UserService;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/user")
    public HttpStatus createUser(@RequestBody AccountCreateRequest request) {
        userService.createAccount(request);

        return HttpStatus.CREATED;
    }


}
