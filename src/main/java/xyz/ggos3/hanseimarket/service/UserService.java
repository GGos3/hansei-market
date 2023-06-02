package xyz.ggos3.hanseimarket.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.ggos3.hanseimarket.domain.user.User;
import xyz.ggos3.hanseimarket.domain.user.UserRepository;
import xyz.ggos3.hanseimarket.dto.user.request.UserCreateRequest;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public void saveUser(UserCreateRequest request) {
        User newUser = new User(
                request.getName(),
                request.getStudentCode(),
                request.getPhoneNumber()
        );

        userRepository.save(newUser);
    }
}
