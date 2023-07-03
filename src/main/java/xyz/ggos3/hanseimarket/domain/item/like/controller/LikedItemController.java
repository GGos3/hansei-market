package xyz.ggos3.hanseimarket.domain.item.like.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import xyz.ggos3.hanseimarket.common.dto.ApiResponse;
import xyz.ggos3.hanseimarket.domain.item.like.LikedItem;
import xyz.ggos3.hanseimarket.domain.item.like.dto.request.AddLikeItemRequest;
import xyz.ggos3.hanseimarket.common.security.annotation.UserAuthorize;
import xyz.ggos3.hanseimarket.domain.item.like.service.LikedItemService;
import xyz.ggos3.hanseimarket.domain.user.dto.response.UserLikedItemsResponse;

import java.util.List;


@Tag(name = "찜 관련 API")
@RestController
@UserAuthorize
@RequiredArgsConstructor
@RequestMapping("/like")
public class LikedItemController {

    private LikedItemService likedItemService;

    @Operation(summary = "찜 목록 추가")
    @PatchMapping
    public ApiResponse<LikedItem> addLikedItem(@AuthenticationPrincipal User user, @RequestBody AddLikeItemRequest request) {
        return ApiResponse.success(likedItemService.addLikedItem(user.getUsername(), request));
    }

    @Operation(summary = "유저의 찜 목록")
    @PostMapping("/user")
    public ApiResponse<List<UserLikedItemsResponse>> getUserLikedItems(@AuthenticationPrincipal User user) {
        return ApiResponse.success(likedItemService.getLikedItemsByUserId(user.getUsername()));
    }
}
