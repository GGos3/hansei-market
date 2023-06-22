package xyz.ggos3.hanseimarket.service.item;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import xyz.ggos3.hanseimarket.domain.item.Item;
import xyz.ggos3.hanseimarket.domain.item.ItemRepository;
import xyz.ggos3.hanseimarket.domain.user.User;
import xyz.ggos3.hanseimarket.dto.item.request.ItemSaveRequest;
import xyz.ggos3.hanseimarket.dto.item.request.ItemStatusUpdateRequest;
import xyz.ggos3.hanseimarket.dto.item.request.ItemUpdateRequest;
import xyz.ggos3.hanseimarket.dto.item.response.ItemResponse;
import xyz.ggos3.hanseimarket.service.user.UserService;
import xyz.ggos3.hanseimarket.service.user.auth.AuthUserService;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final UserService userService;
    private final AuthUserService authUserService;

    @Transactional
    public Item saveItem(String userId, ItemSaveRequest request) {
        User requestUser = authUserService.findByUuid(userId).getUser();

        Item newItem = new Item(
                requestUser,
                request.itemName(),
                request.price(),
                request.description()
        );

        return itemRepository.save(newItem);
    }

    @Transactional
    public Item findItemById(Long id) {
        Item findItem = itemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("id에 맞는 item이 없습니다."));
        findItem.viewCount();

        return findItem;
    }

    @Transactional
    public Item updateItem(String userId, Long itemId, ItemUpdateRequest request) {
        Long id = validateUserIdIsEqualToItemUser(userId, itemId).getId();

        itemRepository.updateItem(
                id,
                request.itemName(),
                request.price(),
                request.description());

        return findItemById(id);
    }

    @Transactional
    public Item updateStatus(String userId, Long itemId, ItemStatusUpdateRequest request) {
        Long id = validateUserIdIsEqualToItemUser(userId, itemId).getId();

        itemRepository.updateStatus(id, request.status());

        return findItemById(id);
    }

    private Item validateUserIdIsEqualToItemUser(String userId, Long itemId) {
        Item item = findItemById(itemId);
        User user = authUserService.findByUuid(userId).getUser();

        if (!item.getUser().getUserId().equals(user.getUserId()))
            throw new IllegalArgumentException("게시글 수정은 본인만 할 수 있습니다.");

        return item;
    }

    @Transactional
    public Item findItemByName(String itemName) {
        return itemRepository.findByItemName(itemName)
                .orElseThrow(() -> new IllegalArgumentException("이름에 맞는 item이 없습니다."));
    }

    @Transactional
    public Item findItemByUserId(String userId) {
        User userByUserId = userService.findUser(userId);
        Item item = itemRepository.findByUser(userByUserId)
                .orElseThrow(() -> new IllegalArgumentException("유저 id에 맞는 item이 없습니다."));
        item.viewCount();

        return item;
    }

    @Transactional
    public void deleteItemById(Long id) {
        findItemById(id);
        itemRepository.deleteById(id);
    }

    @Transactional
    public List<ItemResponse> findAll() {
        return itemRepository.findAll().stream()
                .map(ItemResponse::new)
                .toList();
    }
}
