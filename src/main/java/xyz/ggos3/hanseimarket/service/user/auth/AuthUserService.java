package xyz.ggos3.hanseimarket.service.user.auth;

import org.springframework.stereotype.Service;
import xyz.ggos3.hanseimarket.domain.user.auth.AuthUser;
import xyz.ggos3.hanseimarket.dto.user.auth.request.SignInRequest;
import xyz.ggos3.hanseimarket.dto.user.auth.response.SignInResponse;

@Service
public interface AuthUserService {
    AuthUser findByUserId(String userId);
    AuthUser findByUuid(String uuid);
    SignInResponse signIn(SignInRequest request);
}
