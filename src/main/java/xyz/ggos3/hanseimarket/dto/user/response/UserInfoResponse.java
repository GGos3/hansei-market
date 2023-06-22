package xyz.ggos3.hanseimarket.dto.user.response;

import lombok.Builder;
import lombok.Getter;
import xyz.ggos3.hanseimarket.domain.user.User;
import xyz.ggos3.hanseimarket.domain.user.UserStatus;

import java.sql.Timestamp;

public record UserInfoResponse(
        String name,
        String studentCode,
        String phoneNumber,
        UserStatus status,
        int tradeCount,
        Timestamp createdDate
) {
    public UserInfoResponse(User user) {
        this(
                user.getName(),
                user.getStudentCode(),
                user.getPhoneNumber(),
                user.getStatus(),
                user.getTradeCount(),
                user.getCreatedDate());
    }
}
