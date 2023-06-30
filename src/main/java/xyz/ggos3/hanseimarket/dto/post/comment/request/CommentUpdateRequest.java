package xyz.ggos3.hanseimarket.dto.post.comment.request;

public record CommentUpdateRequest(
        Long commentId,
        String comment
) {
}
