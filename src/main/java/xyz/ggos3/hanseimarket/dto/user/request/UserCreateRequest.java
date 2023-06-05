package xyz.ggos3.hanseimarket.dto.user.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class UserCreateRequest {

    @NotNull
    private final String userId;
    @NotNull
    private final String userPassword;
    @NotNull
    private final String name;
    @NotNull
    private final String studentCode;
    @NotNull
    private final String phoneNumber;

    public UserCreateRequest(String userId, String userPassword, String name, String studentCode, String phoneNumber) {
        this.userId = userId;
        this.userPassword = userPassword;
        this.name = name;
        this.studentCode = studentCode;
        this.phoneNumber = phoneNumber;
    }
}
