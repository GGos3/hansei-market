package xyz.ggos3.hanseimarket.dto.item.like.request;

public record AddLikeItemRequest(
        Long itemId
) {
    public AddLikeItemRequest(String itemId) {
        this(Long.parseLong(itemId));
    }
}
