package xyz.ggos3.hanseimarket.domain.item.dto.request;

public record ItemUpdateRequest(
        String userId,
        String itemName,
        int price,
        String description
) {
}