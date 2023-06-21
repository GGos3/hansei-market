package xyz.ggos3.hanseimarket.dto.user.request;

import lombok.Getter;

@Getter
public class UserIdValidateRequest {
    private String userId;

    public UserIdValidateRequest() {
    }

    public UserIdValidateRequest(String userId) {
        this.userId = userId;
    }
}
