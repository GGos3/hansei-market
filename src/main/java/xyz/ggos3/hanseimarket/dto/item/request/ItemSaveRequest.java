package xyz.ggos3.hanseimarket.dto.item.request;

public record ItemSaveRequest(
        String userId,
        String itemName,
        int price,
        String description
) {
}