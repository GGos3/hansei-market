package xyz.ggos3.hanseimarket.service.item.like;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.ggos3.hanseimarket.domain.item.Item;
import xyz.ggos3.hanseimarket.domain.item.like.LikedItem;
import xyz.ggos3.hanseimarket.domain.item.like.LikedItemRepository;
import xyz.ggos3.hanseimarket.domain.user.User;
import xyz.ggos3.hanseimarket.service.item.ItemService;
import xyz.ggos3.hanseimarket.service.user.UserService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LikedItemService {
    private final LikedItemRepository likedItemRepository;
    private final UserService userService;
    private final ItemService itemService;

    @Transactional
    public LikedItem addLikedItem(String userId, Long itemId) {
        User user = userService.findUser(userId);
        Item item = itemService.findItemById(itemId);

        LikedItem likedItem = new LikedItem(user, item);

        item.increaseLike();
        return likedItemRepository.save(likedItem);
    }

    @Transactional
    public void deleteLikedItem(String userId, Long itemId) {
        userService.findUser(userId);
        Item item = itemService.findItemById(itemId);

        likedItemRepository.findByItem(item);
    }

    @Transactional
    public List<LikedItem> findLikedItemByUserId(String userId) {
        User user = userService.findUser(userId);

        List<LikedItem> likedItems = likedItemRepository.findByUser(user);

        if (likedItems.isEmpty())
            throw new IllegalArgumentException("찜 리스트가 비어있습니다.");

        return likedItems;
    }

    @Transactional
    public LikedItem findLikedItemByItemId(Long id) {
        Item item = itemService.findItemById(id);

        return likedItemRepository.findByItem(item)
                .orElseThrow(() -> new IllegalArgumentException("해당 아이템은 존재하지 않습니다."));
    }
}
