package xyz.ggos3.hanseimarket.dto.item.request;

public record ItemUpdateRequest(
        Long id,
        String userId,
        String itemName,
        int price,
        String description
) {
}