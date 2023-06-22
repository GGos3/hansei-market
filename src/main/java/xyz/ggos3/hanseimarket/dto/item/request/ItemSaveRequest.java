package xyz.ggos3.hanseimarket.dto.item.request;

public record ItemSaveRequest(
        String itemName,
        int price,
        String description
) {
}