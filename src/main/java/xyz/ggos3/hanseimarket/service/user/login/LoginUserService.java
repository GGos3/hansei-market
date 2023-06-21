package xyz.ggos3.hanseimarket.service.user.login;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import xyz.ggos3.hanseimarket.domain.user.login.LoginUser;
import xyz.ggos3.hanseimarket.domain.user.login.LoginUserRepository;
import xyz.ggos3.hanseimarket.dto.user.login.request.SignInRequest;
import xyz.ggos3.hanseimarket.dto.user.login.response.SignInResponse;
import xyz.ggos3.hanseimarket.security.jwt.TokenProvider;


@Service
@RequiredArgsConstructor
public class LoginUserService {
    private final LoginUserRepository loginUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    @Transactional
    public LoginUser findByUserId(String userId) {
        return loginUserRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));
    }

    @Transactional
    public SignInResponse signIn(SignInRequest request) {
        LoginUser user = findByUserId(request.getId());

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword()))
            throw new IllegalArgumentException("아이디와 비밀번호가 일치하지 않습니다");

        String token = tokenProvider.createToken(String.format("%s:%s", user.getUuid(), user.getUserType()));

        return new SignInResponse(user.getUserId(), token);
    }


}
