package xyz.ggos3.hanseimarket.domain.item.like.dto.request;

public record AddLikeItemRequest(
        Long itemId
) {
    public AddLikeItemRequest(String itemId) {
        this(Long.parseLong(itemId));
    }
}
