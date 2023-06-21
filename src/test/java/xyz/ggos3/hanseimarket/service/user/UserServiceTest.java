package xyz.ggos3.hanseimarket.service.user;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import xyz.ggos3.hanseimarket.domain.user.User;
import xyz.ggos3.hanseimarket.domain.user.UserRepository;
import xyz.ggos3.hanseimarket.domain.user.UserStatus;
import xyz.ggos3.hanseimarket.domain.user.login.LoginUser;
import xyz.ggos3.hanseimarket.domain.user.login.LoginUserRepository;
import xyz.ggos3.hanseimarket.dto.user.request.UserCreateRequest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    private final UserService userService;
    private final UserRepository userRepository;
    private final LoginUserRepository loginUserRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceTest(UserService userService, UserRepository userRepository, LoginUserRepository loginUserRepository, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.loginUserRepository = loginUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @AfterEach
    void clear() {
        loginUserRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    @DisplayName("유저 생성")
    void createUserTest() {
        UserCreateRequest request = new UserCreateRequest("test123", "testPass1234", "test", "H1111", "01011111111");

        userService.createAccount(request);

        User user = userRepository.findByUserId(request.getUserId()).get();
        LoginUser loginUser = loginUserRepository.findByUserId(request.getUserId()).get();

        // 저장된 User의 Id 가 request의 Id와 같은지 검증
        assertThat(user.getUserId()).isEqualTo(request.getUserId());
        // 로그인 전용 User의 user필드가가 user의 id와 같은지 검증
        assertThat(loginUser.getUser().getId()).isEqualTo(user.getId());
    }

    @Test
    @DisplayName("유저 비밀번호 암호화")
    void userPasswordEncodeTest() {
        UserCreateRequest request = new UserCreateRequest("test123", "testPass1234", "test", "H1111", "01011111111");

        User user = userService.createAccount(request);

        User findUser = userService.findUser(user.getUserId());

        assertThat(passwordEncoder.matches(request.getUserPassword(), findUser.getUserPassword())).isEqualTo(true);
    }

    @Test()
    @DisplayName("중복 유저 검증")
    void validateCreateUserTest() {
        UserCreateRequest request = new UserCreateRequest("test123", "testPass1234", "test", "H1111", "01011111111");

        userService.createAccount(request);

        Assertions.assertThrows(IllegalArgumentException.class, () -> userService.createAccount(request));
    }

    @Test
    @DisplayName("유저 비활성화")
    void disableUserTest() {
        UserCreateRequest userCreateRequest = new UserCreateRequest("test123", "testPass1234", "test", "H1111", "010111111111");
        userService.createAccount(userCreateRequest);

        userService.disableUser("test123");

        assertThat(userRepository.findByUserId("test123").get().getStatus()).isEqualTo(UserStatus.disable);
    }
}