package xyz.ggos3.hanseimarket.domain.post.comment.dto.request;

public record CommentSaveRequest(
        Long postId,
        String comment
) {
}
