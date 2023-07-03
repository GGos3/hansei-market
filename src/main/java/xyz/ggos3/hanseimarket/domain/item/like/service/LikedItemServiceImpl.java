package xyz.ggos3.hanseimarket.domain.item.like.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.ggos3.hanseimarket.domain.item.domain.Item;
import xyz.ggos3.hanseimarket.domain.item.like.LikedItem;
import xyz.ggos3.hanseimarket.domain.item.like.LikedItemRepository;
import xyz.ggos3.hanseimarket.domain.user.domain.User;
import xyz.ggos3.hanseimarket.domain.item.like.dto.request.AddLikeItemRequest;
import xyz.ggos3.hanseimarket.domain.user.dto.response.UserLikedItemsResponse;
import xyz.ggos3.hanseimarket.domain.item.service.ItemService;
import xyz.ggos3.hanseimarket.domain.user.service.UserService;
import xyz.ggos3.hanseimarket.domain.user.auth.service.AuthUserService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LikedItemServiceImpl implements LikedItemService {

    private final UserService userService;
    private final ItemService itemService;
    private final AuthUserService authUserService;
    private final LikedItemRepository likedItemRepository;

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
    public List<UserLikedItemsResponse> getLikedItemsByUserId(String userId) {
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
