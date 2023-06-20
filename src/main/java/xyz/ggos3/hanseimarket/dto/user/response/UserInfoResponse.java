package xyz.ggos3.hanseimarket.dto.user.response;

import lombok.Getter;
import xyz.ggos3.hanseimarket.domain.user.User;
import xyz.ggos3.hanseimarket.domain.user.UserStatus;

import java.sql.Timestamp;

@Getter
public class UserInfoResponse {
    private String name;
    private String studentCode;
    private String phoneNumber;
    private UserStatus status;
    private int tradeCount;
    private Timestamp createdDate;

    public UserInfoResponse(User user) {
        this.name = user.getName();
        this.studentCode = user.getStudentCode();
        this.phoneNumber = user.getPhoneNumber();
        this.status = user.getStatus();
        this.tradeCount = user.getTradeCount();
    }
}
