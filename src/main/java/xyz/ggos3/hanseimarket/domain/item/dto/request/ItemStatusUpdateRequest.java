package xyz.ggos3.hanseimarket.domain.item.dto.request;

import xyz.ggos3.hanseimarket.domain.item.domain.ItemStatus;

public record ItemStatusUpdateRequest(
        ItemStatus status
) {
}