package xyz.ggos3.hanseimarket.domain.item.service;

import org.springframework.stereotype.Service;
import xyz.ggos3.hanseimarket.domain.item.domain.Item;
import xyz.ggos3.hanseimarket.domain.user.domain.User;
import xyz.ggos3.hanseimarket.domain.item.dto.request.ItemSaveRequest;
import xyz.ggos3.hanseimarket.domain.item.dto.request.ItemStatusUpdateRequest;
import xyz.ggos3.hanseimarket.domain.item.dto.request.ItemUpdateRequest;
import xyz.ggos3.hanseimarket.domain.item.dto.response.ItemResponse;

import java.util.List;

@Service
public interface ItemService {
    Item saveItem(String userId, ItemSaveRequest request);
    ItemResponse getItem(Long id);
    Item findItemById(Long id);
    Item findItemByUserId(User user);
    Item updateItem(String userId, Long itemId, ItemUpdateRequest request);
    Item updateStatus(String userId, Long itemId, ItemStatusUpdateRequest request);
    List<ItemResponse> findAll();
    void clearAll();
}
