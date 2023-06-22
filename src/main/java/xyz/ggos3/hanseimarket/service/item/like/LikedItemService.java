package xyz.ggos3.hanseimarket.service.item.like;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.ggos3.hanseimarket.domain.item.Item;
import xyz.ggos3.hanseimarket.domain.item.like.LikedItem;
import xyz.ggos3.hanseimarket.domain.item.like.LikedItemRepository;
import xyz.ggos3.hanseimarket.domain.user.User;
import xyz.ggos3.hanseimarket.dto.item.like.request.AddLikeItemRequest;
import xyz.ggos3.hanseimarket.dto.user.response.UserLikedItemsResponse;
import xyz.ggos3.hanseimarket.service.item.ItemService;
import xyz.ggos3.hanseimarket.service.user.UserService;
import xyz.ggos3.hanseimarket.service.user.auth.AuthUserService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LikedItemService {

    private final LikedItemRepository likedItemRepository;
    private final UserService userService;
    private final ItemService itemService;
    private final AuthUserService authUserService;

    @Transactional
    public LikedItem addLikedItem(String userId, AddLikeItemRequest request) {
        User user = authUserService.findByUuid(userId).getUser();
        Item item = itemService.findItemById(request.itemId());

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
    public List<UserLikedItemsResponse> findLikedItemsByUserId(String userId) {
        return likedItemRepository.findByUser(authUserService.findByUuid(userId).getUser())
                .stream().map(item ->
                        new UserLikedItemsResponse(
                                item.getItem().getId(),
                                item.getItem().getItemName()
                        ))
                .toList();
    }

    @Transactional
    public LikedItem findLikedItemByItemId(Long id) {
        Item item = itemService.findItemById(id);

        return likedItemRepository.findByItem(item)
                .orElseThrow(() -> new IllegalArgumentException("해당 아이템은 존재하지 않습니다."));
    }
}
