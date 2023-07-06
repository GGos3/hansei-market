package xyz.ggos3.hanseimarket.domain.post.comment.dto.response;

import lombok.Builder;
import xyz.ggos3.hanseimarket.domain.post.comment.Comment;

import java.sql.Timestamp;

public record CommentResponse(
        String userId,
        String comment,
        Timestamp createDate
) {
    public CommentResponse(Comment comment) {
        this(
                comment.getAuthUser().getUserId(),
                comment.getComment(),
                comment.getCreateDate()
        );
    }
}
