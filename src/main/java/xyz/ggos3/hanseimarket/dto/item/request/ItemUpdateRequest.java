package xyz.ggos3.hanseimarket.dto.item.request;

import lombok.Getter;

@Getter
public class ItemUpdateRequest {
    private Long id;
    private String userId;
    private String itemName;
    private int price;
    private String description;

    public ItemUpdateRequest(Long id, String userId, String itemName, int price, String description) {
        this.id = id;
        this.userId = userId;
        this.itemName = itemName;
        this.price = price;
        this.description = description;
    }
}
