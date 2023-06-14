package xyz.ggos3.hanseimarket.dto.user.response;

import lombok.Getter;
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

    public UserInfoResponse(String name, String studentCode, String phoneNumber, UserStatus status, int tradeCount, Timestamp createdDate) {
        this.name = name;
        this.studentCode = studentCode;
        this.phoneNumber = phoneNumber;
        this.status = status;
        this.tradeCount = tradeCount;
        this.createdDate = createdDate;
    }
}
