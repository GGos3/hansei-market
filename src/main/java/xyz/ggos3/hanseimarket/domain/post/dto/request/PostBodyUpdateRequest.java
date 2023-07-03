package xyz.ggos3.hanseimarket.domain.post.dto.request;

public record PostBodyUpdateRequest(
        Long postId,
        String postBody
) {
}
