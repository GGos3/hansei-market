package xyz.ggos3.hanseimarket.controller.item.like;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import xyz.ggos3.hanseimarket.dto.ApiResponse;
import xyz.ggos3.hanseimarket.dto.item.like.request.AddLikeItemRequest;
import xyz.ggos3.hanseimarket.security.annotation.UserAuthorize;
import xyz.ggos3.hanseimarket.service.item.like.LikedItemService;


@Tag(name = "찜 관련 API")
@RestController
@UserAuthorize
@RequiredArgsConstructor
@RequestMapping("/like")
public class LikedItemController {

    private LikedItemService likedItemService;

    @Operation(summary = "찜 목록 추가")
    @PatchMapping
    public ApiResponse addLikedItem(@AuthenticationPrincipal User user, @RequestBody AddLikeItemRequest request) {
        return ApiResponse.success(likedItemService.addLikedItem(user.getUsername(), request));
    }

    @Operation(summary = "유저의 찜 목록")
    @PostMapping("/user")
    public ApiResponse getUserLikedItems(@AuthenticationPrincipal User user) {
        return ApiResponse.success(likedItemService.findLikedItemsByUserId(user.getUsername()));
    }
}
