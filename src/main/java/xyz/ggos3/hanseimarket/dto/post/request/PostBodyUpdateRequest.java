package xyz.ggos3.hanseimarket.dto.post.request;

public record PostBodyUpdateRequest(
        Long postId,
        String postBody
) {
}
