package xyz.ggos3.hanseimarket.dto.post.comment.request;

public record CommentSaveRequest(
        Long postId,
        String comment
) {
}
