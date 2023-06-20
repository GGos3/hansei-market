package xyz.ggos3.hanseimarket.controller.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import xyz.ggos3.hanseimarket.domain.user.User;
import xyz.ggos3.hanseimarket.dto.user.request.UserCreateRequest;
import xyz.ggos3.hanseimarket.dto.user.response.UserInfoResponse;
import xyz.ggos3.hanseimarket.service.user.UserService;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/user/validate")
    public ResponseEntity<?> validateUserId(@RequestBody String userId) {
        userService.validateUser(userId);

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
