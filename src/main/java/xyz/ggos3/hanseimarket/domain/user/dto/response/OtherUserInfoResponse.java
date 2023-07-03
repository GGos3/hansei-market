package xyz.ggos3.hanseimarket.domain.user.dto.response;

import xyz.ggos3.hanseimarket.domain.user.domain.User;
import xyz.ggos3.hanseimarket.domain.user.domain.UserStatus;

import java.sql.Timestamp;

public record OtherUserInfoResponse(
        String name,
        String studentCode,
        UserStatus status,
        int tradeCount,
        Timestamp createdDate
) {
    public OtherUserInfoResponse(User user) {
        this(
                user.getName(),
                user.getStudentCode(),
                user.getStatus(),
                user.getTradeCount(),
                user.getCreatedDate()
        );
    }
}
