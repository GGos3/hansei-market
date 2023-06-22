package xyz.ggos3.hanseimarket.service.user.auth;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import xyz.ggos3.hanseimarket.domain.user.auth.AuthUser;
import xyz.ggos3.hanseimarket.domain.user.auth.AuthUserRepository;
import xyz.ggos3.hanseimarket.dto.user.auth.request.SignInRequest;
import xyz.ggos3.hanseimarket.dto.user.auth.response.SignInResponse;
import xyz.ggos3.hanseimarket.security.jwt.TokenProvider;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthUserService {
    private final AuthUserRepository authUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    @Transactional
    public AuthUser findByUserId(String userId) {
        return authUserRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));
    }


    @Transactional
    public SignInResponse signIn(SignInRequest request) {
        AuthUser user = findByUserId(request.id());

        if (!passwordEncoder.matches(request.password(), user.getPassword()))
            throw new IllegalArgumentException("아이디와 비밀번호가 일치하지 않습니다");

        log.info("userGetType={}", user.getUserType());

        String token = tokenProvider.createToken(String.format("%s:%s", user.getUuid(), user.getUserType()));

        return new SignInResponse(user.getUserId(), token);
    }


}
