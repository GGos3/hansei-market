package xyz.ggos3.hanseimarket.dto.item.request;

import lombok.Getter;
import xyz.ggos3.hanseimarket.domain.item.ItemStatus;

@Getter
public class ItemStatusUpdateRequest {
    private Long id;
    private ItemStatus status;

    public ItemStatusUpdateRequest(Long id, ItemStatus status) {
        this.id = id;
        this.status = status;
    }
}
