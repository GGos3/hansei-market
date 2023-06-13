package xyz.ggos3.hanseimarket.service.item;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.ggos3.hanseimarket.domain.item.Item;
import xyz.ggos3.hanseimarket.domain.item.ItemRepository;
import xyz.ggos3.hanseimarket.domain.user.User;
import xyz.ggos3.hanseimarket.dto.item.request.ItemSaveRequest;
import xyz.ggos3.hanseimarket.dto.item.request.ItemUpdateRequest;
import xyz.ggos3.hanseimarket.service.user.UserService;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final UserService userService;

    @Transactional
    public void saveItem(ItemSaveRequest request) {
        User requestUser = userService.findUserByUserId(request.getUserId());
        Item newItem = new Item(
                requestUser,
                request.getItemName(),
                request.getPrice(),
                request.getDescription()
        );

        itemRepository.save(newItem);
    }

    @Transactional
    public void updateItem(ItemUpdateRequest request) {
        Item item = findItemById(request.getId());
        itemRepository.updateItem(
                request.getId(),
                request.getItemName(),
                request.getPrice(),
                request.getDescription());
    }

    @Transactional
    public Item findItemByName(String itemName) {
        return itemRepository.findByItemName(itemName)
                .orElseThrow(() -> new IllegalArgumentException("이름에 맞는 item이 없습니다."));
    }

    @Transactional
    public Item findItemById(Long id) {
        Item findItem = itemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("id에 맞는 item이 없습니다."));
        findItem.viewCount();

        return findItem;
    }

    @Transactional
    public Item findItemByUserId(String userId) {
        User userByUserId = userService.findUserByUserId(userId);
        Item item = itemRepository.findByUser(userByUserId)
                .orElseThrow(() -> new IllegalArgumentException("유저 id에 맞는 item이 없습니다."));
        item.viewCount();

        return item;
    }
}
