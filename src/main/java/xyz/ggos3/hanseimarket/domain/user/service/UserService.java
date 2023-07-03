package xyz.ggos3.hanseimarket.domain.user.service;

import org.springframework.stereotype.Service;
import xyz.ggos3.hanseimarket.domain.user.domain.User;
import xyz.ggos3.hanseimarket.domain.user.auth.dto.request.SignUpRequest;
import xyz.ggos3.hanseimarket.domain.user.dto.response.OtherUserInfoResponse;
import xyz.ggos3.hanseimarket.domain.user.dto.response.UserInfoResponse;

@Service
public interface UserService {
    User createAccount(SignUpRequest request);

    UserInfoResponse findUserInfo(String userId);

    OtherUserInfoResponse findOtherUserInfo(String userId);

    User findUser(String userId);

    User findDisableUser(String userId);

    void enableUser(String userId);

    void disableUser(String userId);

    void clearAll();

    void validateUser(String userId);
}
