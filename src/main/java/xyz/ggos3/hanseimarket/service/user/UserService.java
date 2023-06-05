package xyz.ggos3.hanseimarket.service.user;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import xyz.ggos3.hanseimarket.domain.user.User;
import xyz.ggos3.hanseimarket.domain.user.UserRepository;
import xyz.ggos3.hanseimarket.domain.user.UserStatus;
import xyz.ggos3.hanseimarket.domain.user.login.LoginUser;
import xyz.ggos3.hanseimarket.domain.user.login.LoginUserRepository;
import xyz.ggos3.hanseimarket.dto.user.request.UserCreateRequest;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final LoginUserRepository loginUserRepository;

    @Transactional
    public void createAccount(UserCreateRequest request) {
        User newUser = new User(
                request.getUserId(),
                request.getUserPassword(),
                request.getName(),
                request.getStudentCode(),
                request.getPhoneNumber()
        );
        LoginUser newLoginUser = new LoginUser(newUser);

        validateLoginId(newUser);

        userRepository.save(newUser);
        loginUserRepository.save(newLoginUser);
    }

    @Transactional
    public User findUserByUserId(String accountId) {
        return userRepository.findByUserId(accountId)
                .orElseThrow(() -> new IllegalArgumentException("잘못된 id 입니다."));
    }

    private void validateLoginId(User user) {
        userRepository.findByUserId(user.getUserId())
                .filter(a -> a.getStatus().equals(UserStatus.enable))
                .ifPresent(account -> {
                    throw new IllegalArgumentException("이미 존재하는 아이디입니다.");
                });
    }
}
