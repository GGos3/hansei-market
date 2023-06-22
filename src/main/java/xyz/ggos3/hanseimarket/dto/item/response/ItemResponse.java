package xyz.ggos3.hanseimarket.dto.item.response;

import xyz.ggos3.hanseimarket.domain.item.Item;
import xyz.ggos3.hanseimarket.domain.item.ItemStatus;

import java.sql.Timestamp;

public record ItemResponse(
        Long id,
        String user,
        String itemName,
        String description,
        int likeCount,
        int price,
        int view,
        ItemStatus status,
        Timestamp createdDate
) {
    public ItemResponse(Item item) {
        this(
                item.getId(),
                item.getUser().getUserId(),
                item.getItemName(),
                item.getDescription(),
                item.getLikeCount(),
                item.getPrice(),
                item.getPrice(),
                item.getStatus(),
                item.getCreatedDate()
        );
    }
}
