package xyz.ggos3.hanseimarket.service.user;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import xyz.ggos3.hanseimarket.domain.user.User;
import xyz.ggos3.hanseimarket.domain.user.UserRepository;
import xyz.ggos3.hanseimarket.domain.user.UserStatus;
import xyz.ggos3.hanseimarket.domain.user.auth.AuthUser;
import xyz.ggos3.hanseimarket.domain.user.auth.AuthUserRepository;
import xyz.ggos3.hanseimarket.dto.user.auth.request.SignUpRequest;
import xyz.ggos3.hanseimarket.dto.user.response.UserInfoResponse;
import xyz.ggos3.hanseimarket.service.user.auth.AuthUserService;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final AuthUserRepository authUserRepository;
    private final AuthUserService authUserService;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public User createAccount(SignUpRequest request) {
        User newUser = new User(
                request.userId(),
                passwordEncoder.encode(request.userPassword()),
                request.name(),
                request.studentCode(),
                request.phoneNumber()
        );
        AuthUser newAuthUser = new AuthUser(newUser);

        validateUser(newUser.getUserId());

        log.info("New User = {}", request.userId());
        authUserRepository.save(newAuthUser);
        return userRepository.save(newUser);
    }

    @Transactional
    public UserInfoResponse findUserInfo(String userId) {
        return new UserInfoResponse(
                findUser(authUserService.findByUuid(UUID.fromString(userId)).getUserId()));
    }

    @Transactional
    public User findUser(String userId) {
        return userRepository.findByUserId(userId)
                .filter(user -> user.getStatus().equals(UserStatus.enable))
                .orElseThrow(() -> new IllegalArgumentException("id에 맞는 User가 없습니다."));
    }

    @Transactional
    public void disableUser(String userId) {
        userRepository.updateUserStatus(findUser(userId).getId(), UserStatus.disable);
    }

    @Transactional
    public void enableUser(String userId) {
        userRepository.updateUserStatus(findUser(userId).getId(), UserStatus.enable);
    }

    @Transactional
    public void deleteUserById(String userId) {
        userRepository.deleteUserByUserId(userId);
    }

    @Transactional
    public void validateUser(String userId) {
        userRepository.findByUserId(userId)
                .filter(a -> a.getStatus().equals(UserStatus.enable))
                .ifPresent(a -> {
                    throw new IllegalArgumentException("이미 존재하는 ID 입니다");
                });
    }
}
