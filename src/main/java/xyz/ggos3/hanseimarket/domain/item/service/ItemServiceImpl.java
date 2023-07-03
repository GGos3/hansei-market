package xyz.ggos3.hanseimarket.domain.item.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import xyz.ggos3.hanseimarket.domain.item.domain.Item;
import xyz.ggos3.hanseimarket.domain.item.domain.ItemRepository;
import xyz.ggos3.hanseimarket.domain.item.like.LikedItemRepository;
import xyz.ggos3.hanseimarket.domain.user.domain.User;
import xyz.ggos3.hanseimarket.domain.item.dto.request.ItemSaveRequest;
import xyz.ggos3.hanseimarket.domain.item.dto.request.ItemStatusUpdateRequest;
import xyz.ggos3.hanseimarket.domain.item.dto.request.ItemUpdateRequest;
import xyz.ggos3.hanseimarket.domain.item.dto.response.ItemResponse;
import xyz.ggos3.hanseimarket.domain.user.auth.service.AuthUserService;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final AuthUserService authUserService;
    private final ItemRepository itemRepository;
    private final LikedItemRepository likedItemRepository;

    @Override
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

    @Override
    @Transactional
    public ItemResponse getItem(Long id) {
        Item item = findItemById(id);
        item.viewCount();

        return new ItemResponse(item);
    }

    @Override
    @Transactional
    public Item findItemById(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("id에 맞는 item이 없습니다."));
    }

    @Override
    @Transactional
    public Item findItemByUserId(User user) {
        return itemRepository.findByUser(user)
                .orElseThrow(() -> new IllegalArgumentException("유저 id에 맞는 item이 없습니다."));
    }

    @Override
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

    @Override
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

    @Override
    @Transactional
    public List<ItemResponse> findAll() {
        return itemRepository.findAll().stream()
                .map(ItemResponse::new)
                .toList();
    }

    @Override
    @Transactional
    public void clearAll() {
        likedItemRepository.deleteAll();
        itemRepository.deleteAll();
    }
}
