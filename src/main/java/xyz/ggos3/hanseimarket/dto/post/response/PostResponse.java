package xyz.ggos3.hanseimarket.dto.post.response;

import lombok.Builder;
import xyz.ggos3.hanseimarket.dto.post.comment.response.CommentResponse;

import java.sql.Timestamp;
import java.util.List;

@Builder
public record PostResponse(
        Long postId,
        String userName,
        String postName,
        String postBody,
        List<CommentResponse> postComments,
        Timestamp createDate
) {
}
