package xyz.ggos3.hanseimarket.domain.post.dto.request;

public record PostNameUpdateRequest(
        Long postId,
        String postName
) {
}
