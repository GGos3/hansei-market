package xyz.ggos3.hanseimarket.dto.item.request;

import xyz.ggos3.hanseimarket.domain.item.ItemStatus;

public record ItemStatusUpdateRequest(
        Long id,
        ItemStatus status
) {
}