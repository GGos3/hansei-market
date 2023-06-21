package xyz.ggos3.hanseimarket.dto.user.login.response;

import lombok.Getter;

@Getter
public class SignInResponse {
    private String userId;
    private String token;

    public SignInResponse(String userId, String token) {
        this.userId = userId;
        this.token = token;
    }
}
