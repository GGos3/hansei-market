package xyz.ggos3.hanseimarket.dto.user.account.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import xyz.ggos3.hanseimarket.vo.user.UserCreateVO;

@Getter
public class AccountCreateRequest {

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

    public AccountCreateRequest(String accountId, String accountPassword, String name, String studentCode, String phoneNumber) {
        this.accountId = accountId;
        this.accountPassword = accountPassword;
        this.name = name;
        this.studentCode = studentCode;
        this.phoneNumber = phoneNumber;
    }

    public UserCreateVO getUserCreateRequest() {
        return new UserCreateVO(this.name, this.studentCode, this.phoneNumber);
    }

}
