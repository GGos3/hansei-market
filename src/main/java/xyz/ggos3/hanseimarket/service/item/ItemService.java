package xyz.ggos3.hanseimarket.service.item;

import org.springframework.stereotype.Service;
import xyz.ggos3.hanseimarket.domain.item.Item;
import xyz.ggos3.hanseimarket.domain.user.User;
import xyz.ggos3.hanseimarket.dto.item.request.ItemSaveRequest;
import xyz.ggos3.hanseimarket.dto.item.request.ItemStatusUpdateRequest;
import xyz.ggos3.hanseimarket.dto.item.request.ItemUpdateRequest;
import xyz.ggos3.hanseimarket.dto.item.response.ItemResponse;

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
