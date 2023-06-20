package xyz.ggos3.hanseimarket.controller.item.like;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.ggos3.hanseimarket.domain.item.like.LikedItem;
import xyz.ggos3.hanseimarket.service.item.like.LikedItemService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LikedItemController {
    private LikedItemService likedItemService;
    @PostMapping("/{userId}/liked")
    public List<LikedItem> getLikedItems(@PathVariable String userId) {
        return likedItemService.findLikedItemByUserId(userId);
    }
}
