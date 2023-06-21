package xyz.ggos3.hanseimarket.controller.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import xyz.ggos3.hanseimarket.domain.user.User;
import xyz.ggos3.hanseimarket.dto.user.request.UserCreateRequest;
import xyz.ggos3.hanseimarket.dto.user.request.UserIdValidateRequest;
import xyz.ggos3.hanseimarket.dto.user.response.UserInfoResponse;
import xyz.ggos3.hanseimarket.service.user.UserService;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/user/validate")
    public ResponseEntity<User> validateUserId(@RequestBody UserIdValidateRequest request) {
        userService.validateUser(request.getUserId());
        log.info("validate userId={}", request.getUserId());

        return ResponseEntity.ok().build();
    }

    @PostMapping("/user/register")
    public ResponseEntity<User> createUser(@RequestBody UserCreateRequest request) {
        userService.createAccount(request);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/user/info")
    public UserInfoResponse showUserInfo(@RequestBody String userId) {
        return userService.findUserInfo(userId);
    }
}
