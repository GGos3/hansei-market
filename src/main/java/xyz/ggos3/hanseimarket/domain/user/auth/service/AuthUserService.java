package xyz.ggos3.hanseimarket.domain.user.auth.service;

import org.springframework.stereotype.Service;
import xyz.ggos3.hanseimarket.domain.user.auth.domain.AuthUser;
import xyz.ggos3.hanseimarket.domain.user.auth.dto.request.SignInRequest;
import xyz.ggos3.hanseimarket.domain.user.auth.dto.response.SignInResponse;

@Service
public interface AuthUserService {
    AuthUser findByUserId(String userId);
    AuthUser findByUuid(String uuid);
    SignInResponse signIn(SignInRequest request);
}
