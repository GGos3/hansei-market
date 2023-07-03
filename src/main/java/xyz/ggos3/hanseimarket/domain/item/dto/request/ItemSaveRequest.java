package xyz.ggos3.hanseimarket.domain.item.dto.request;

public record ItemSaveRequest(
        String itemName,
        int price,
        String description
) {
}