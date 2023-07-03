package xyz.ggos3.hanseimarket.domain.post.comment.dto.response;

import lombok.Builder;

import java.sql.Timestamp;

@Builder
public record CommentResponse(
        String userId,
        String comment,
        Timestamp createDate
) {
}
