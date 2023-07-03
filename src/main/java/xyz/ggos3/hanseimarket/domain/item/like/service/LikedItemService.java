package xyz.ggos3.hanseimarket.domain.item.like.service;

import org.springframework.stereotype.Service;
import xyz.ggos3.hanseimarket.domain.item.like.LikedItem;
import xyz.ggos3.hanseimarket.domain.item.like.dto.request.AddLikeItemRequest;
import xyz.ggos3.hanseimarket.domain.user.dto.response.UserLikedItemsResponse;

import java.util.List;

@Service
public interface LikedItemService {
    LikedItem addLikedItem(String userId, AddLikeItemRequest request);

    LikedItem findLikedItemByItemId(Long id);

    List<UserLikedItemsResponse> getLikedItemsByUserId(String userId);

    void deleteLikedItem(String userId, Long itemId);
}
