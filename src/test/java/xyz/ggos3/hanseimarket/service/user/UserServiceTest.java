package xyz.ggos3.hanseimarket.service.user;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import xyz.ggos3.hanseimarket.domain.user.domain.User;
import xyz.ggos3.hanseimarket.domain.user.domain.UserRepository;
import xyz.ggos3.hanseimarket.domain.user.domain.UserStatus;
import xyz.ggos3.hanseimarket.domain.user.auth.domain.AuthUser;
import xyz.ggos3.hanseimarket.domain.user.auth.domain.AuthUserRepository;
import xyz.ggos3.hanseimarket.domain.user.service.UserServiceImpl;
import xyz.ggos3.hanseimarket.domain.user.auth.dto.request.SignUpRequest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    private final UserServiceImpl userService;
    private final UserRepository userRepository;
    private final AuthUserRepository authUserRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceTest(UserServiceImpl userService, UserRepository userRepository, AuthUserRepository authUserRepository, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.authUserRepository = authUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @AfterEach
    void clear() {
        authUserRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    @DisplayName("유저 생성")
    void createUserTest() {
        SignUpRequest request = new SignUpRequest("test123", "testPass1234", "test", "H1111", "01011111111");

        userService.createAccount(request);

        User user = userRepository.findByUserId(request.userId()).get();
        AuthUser authUser = authUserRepository.findByUserId(request.userId()).get();

        // 저장된 User의 Id 가 request의 Id와 같은지 검증
        assertThat(user.getUserId()).isEqualTo(request.userId());
        // 로그인 전용 User의 user필드가가 user의 id와 같은지 검증
        assertThat(authUser.getUser().getId()).isEqualTo(user.getId());
    }

    @Test
    @DisplayName("유저 비밀번호 암호화")
    void userPasswordEncodeTest() {
        SignUpRequest request = new SignUpRequest("test123", "testPass1234", "test", "H1111", "01011111111");

        User user = userService.createAccount(request);

        User findUser = userService.findUser(user.getUserId());

        assertThat(passwordEncoder.matches(request.userPassword(), findUser.getUserPassword())).isEqualTo(true);
    }

    @Test()
    @DisplayName("중복 유저 검증")
    void validateCreateUserTest() {
        SignUpRequest request = new SignUpRequest("test123", "testPass1234", "test", "H1111", "01011111111");

        userService.createAccount(request);

        Assertions.assertThrows(IllegalArgumentException.class, () -> userService.createAccount(request));
    }

    @Test
    @DisplayName("유저 비활성화")
    void disableUserTest() {
        SignUpRequest signUPREquest = new SignUpRequest("test123", "testPass1234", "test", "H1111", "010111111111");
        userService.createAccount(signUPREquest);

        userService.disableUser("test123");

        assertThat(userRepository.findByUserId("test123").get().getStatus()).isEqualTo(UserStatus.disable);
    }
}