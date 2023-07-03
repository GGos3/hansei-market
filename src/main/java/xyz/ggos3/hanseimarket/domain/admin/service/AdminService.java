package xyz.ggos3.hanseimarket.domain.admin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.ggos3.hanseimarket.domain.user.domain.User;
import xyz.ggos3.hanseimarket.domain.user.domain.UserRepository;
import xyz.ggos3.hanseimarket.domain.user.auth.domain.AuthUserRepository;
import xyz.ggos3.hanseimarket.domain.user.service.UserService;
import xyz.ggos3.hanseimarket.domain.user.auth.service.AuthUserService;

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
