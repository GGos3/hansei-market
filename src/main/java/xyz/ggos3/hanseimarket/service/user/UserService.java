package xyz.ggos3.hanseimarket.service.user;

import org.springframework.stereotype.Service;
import xyz.ggos3.hanseimarket.domain.user.User;
import xyz.ggos3.hanseimarket.dto.user.auth.request.SignUpRequest;
import xyz.ggos3.hanseimarket.dto.user.response.OtherUserInfoResponse;
import xyz.ggos3.hanseimarket.dto.user.response.UserInfoResponse;

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
}
