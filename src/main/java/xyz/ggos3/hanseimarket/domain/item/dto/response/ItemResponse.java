package xyz.ggos3.hanseimarket.domain.item.dto.response;

import xyz.ggos3.hanseimarket.domain.item.domain.Item;
import xyz.ggos3.hanseimarket.domain.item.domain.ItemStatus;

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
                item.getView(),
                item.getStatus(),
                item.getCreatedDate()
        );
    }
}
