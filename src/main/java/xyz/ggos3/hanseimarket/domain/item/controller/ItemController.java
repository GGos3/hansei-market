package xyz.ggos3.hanseimarket.domain.item.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import xyz.ggos3.hanseimarket.common.dto.ApiResponse;
import xyz.ggos3.hanseimarket.domain.item.dto.request.ItemSaveRequest;
import xyz.ggos3.hanseimarket.domain.item.dto.request.ItemStatusUpdateRequest;
import xyz.ggos3.hanseimarket.domain.item.dto.request.ItemUpdateRequest;
import xyz.ggos3.hanseimarket.domain.item.dto.response.ItemResponse;
import xyz.ggos3.hanseimarket.common.security.annotation.UserAuthorize;
import xyz.ggos3.hanseimarket.domain.item.service.ItemService;

import java.util.List;

@Tag(name = "물건 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/item")
public class ItemController {

    private final ItemService itemService;

    @UserAuthorize
    @Operation(summary = "물건 등록")
    @PostMapping("/new")
    public ApiResponse<ItemResponse> createItem(@AuthenticationPrincipal User user, @RequestBody ItemSaveRequest request) {
        return ApiResponse.success(new ItemResponse(itemService.saveItem(user.getUsername(), request)));
    }

    @Operation(summary = "물건 조회", description = "로그인이 필요하지 않음")
    @GetMapping("/{itemId}")
    public ApiResponse<ItemResponse> getItem(@PathVariable Long itemId) {
        return ApiResponse.success(new ItemResponse(itemService.findItemById(itemId)));
    }

    @UserAuthorize
    @Operation(summary = "물건 수정")
    @PutMapping("/update/{itemId}")
    public ApiResponse<ItemResponse> updateItem(@AuthenticationPrincipal User user, @PathVariable Long itemId, ItemUpdateRequest request) {
        return ApiResponse.success(new ItemResponse(itemService.updateItem(user.getUsername(), itemId, request)));
    }

    @UserAuthorize
    @Operation(summary = "물건 상태 변경")
    @PatchMapping("/update/{itemId}")
    public ApiResponse<ItemResponse> updateItemStatus(@AuthenticationPrincipal User user, @PathVariable Long itemId, ItemStatusUpdateRequest request) {
        return ApiResponse.success(new ItemResponse(itemService.updateStatus(user.getUsername(), itemId,request)));
    }

    @Operation(summary = "모든 물건 조회")
    @PostMapping("/all")
    public ApiResponse<List<ItemResponse>> getAllItem() {
        return ApiResponse.success(itemService.findAll());
    }
}
