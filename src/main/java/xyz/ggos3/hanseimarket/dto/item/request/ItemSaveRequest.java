package xyz.ggos3.hanseimarket.dto.item.request;

import lombok.Getter;

@Getter
public class ItemSaveRequest {
    private String userId;
    private String itemName;
    private int price;
    private String description;

    public ItemSaveRequest(String userId, String itemName, int price, String description) {
        this.userId = userId;
        this.itemName = itemName;
        this.price = price;
        this.description = description;
    }
}
