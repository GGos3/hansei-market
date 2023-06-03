package xyz.ggos3.hanseimarket.dto.user.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class UserCreateRequest {

    @NotNull
    private final String accountId;
    @NotNull
    private final String accountPassword;
    @NotNull
    private final String name;
    @NotNull
    private final String studentCode;
    @NotNull
    private final String phoneNumber;

    public UserCreateRequest(String accountId, String accountPassword, String name, String studentCode, String phoneNumber) {
        this.accountId = accountId;
        this.accountPassword = accountPassword;
        this.name = name;
        this.studentCode = studentCode;
        this.phoneNumber = phoneNumber;
    }
}
