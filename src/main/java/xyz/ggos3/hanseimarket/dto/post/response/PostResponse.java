package xyz.ggos3.hanseimarket.dto.post.response;

import lombok.Builder;
import xyz.ggos3.hanseimarket.domain.post.Post;

import java.sql.Timestamp;

public record PostResponse(
        Long postId,
        String userName,
        String postName,
        String postBody,
        Timestamp createDate
) {
    public PostResponse(Post post) {
        this(
                post.getId(),
                post.getAuthUser().getUserId(),
                post.getPostName(),
                post.getPostBody(),
                post.getCreateDate()
        );
    }
}
