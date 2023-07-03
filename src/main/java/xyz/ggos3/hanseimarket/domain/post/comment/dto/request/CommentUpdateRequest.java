package xyz.ggos3.hanseimarket.domain.post.comment.dto.request;

public record CommentUpdateRequest(
        Long commentId,
        String comment
) {
}
