package xyz.ggos3.hanseimarket.service.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.ggos3.hanseimarket.domain.user.User;
import xyz.ggos3.hanseimarket.domain.user.UserRepository;
import xyz.ggos3.hanseimarket.domain.user.auth.AuthUserRepository;
import xyz.ggos3.hanseimarket.service.user.UserService;
import xyz.ggos3.hanseimarket.service.user.auth.AuthUserService;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserService userService;
    private final AuthUserService authUserService;
    private final UserRepository userRepository;
    private final AuthUserRepository authUserRepository;

    public User findUser(String userId) {
        return userRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));
    }
}
