package xyz.ggos3.hanseimarket.dto.user.login.request;

import lombok.Getter;

@Getter
public class SignInRequest {
    private String id;
    private String Password;

    public SignInRequest(String id, String password) {
        this.id = id;
        Password = password;
    }
}
