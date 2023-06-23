package xyz.ggos3.hanseimarket.controller.item;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import xyz.ggos3.hanseimarket.dto.ApiResponse;
import xyz.ggos3.hanseimarket.dto.item.request.ItemSaveRequest;
import xyz.ggos3.hanseimarket.dto.item.request.ItemStatusUpdateRequest;
import xyz.ggos3.hanseimarket.dto.item.request.ItemUpdateRequest;
import xyz.ggos3.hanseimarket.dto.item.response.ItemResponse;
import xyz.ggos3.hanseimarket.security.annotation.UserAuthorize;
import xyz.ggos3.hanseimarket.service.item.ItemService;

@Tag(name = "물건 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/item")
public class ItemController {

    private final ItemService itemService;

    @UserAuthorize
    @Operation(summary = "물건 등록", responses = {@io.swagger.v3.oas.annotations.responses.ApiResponse(content = @Content(schema = @Schema(implementation = ItemResponse.class)))})
    @PostMapping("/new")
    public ApiResponse createItem(@AuthenticationPrincipal User user, @RequestBody ItemSaveRequest request) {
        return ApiResponse.success(new ItemResponse(itemService.saveItem(user.getUsername(), request)));
    }

    @Operation(summary = "물건 조회", description = "로그인이 필요하지 않음", responses = {@io.swagger.v3.oas.annotations.responses.ApiResponse(content = @Content(schema = @Schema(implementation = ItemResponse.class)))})
    @GetMapping("/{itemId}")
    public ApiResponse getItem(@PathVariable Long itemId) {
        return ApiResponse.success(new ItemResponse(itemService.findItemById(itemId)));
    }

    @UserAuthorize
    @Operation(summary = "물건 수정", responses = {@io.swagger.v3.oas.annotations.responses.ApiResponse(content = @Content(schema = @Schema(implementation = ItemResponse.class)))})
    @PutMapping("/update/{itemId}")
    public ApiResponse updateItem(@AuthenticationPrincipal User user, @PathVariable Long itemId, ItemUpdateRequest request) {
        return ApiResponse.success(new ItemResponse(itemService.updateItem(user.getUsername(), itemId, request)));
    }

    @UserAuthorize
    @Operation(summary = "물건 상태 변경", responses = {@io.swagger.v3.oas.annotations.responses.ApiResponse(content = @Content(schema = @Schema(implementation = ItemResponse.class)))})
    @PatchMapping("/update/{itemId}")
    public ApiResponse updateItemStatus(@AuthenticationPrincipal User user, @PathVariable Long itemId, ItemStatusUpdateRequest request) {
        return ApiResponse.success(new ItemResponse(itemService.updateStatus(user.getUsername(), itemId,request)));
    }

    @Operation(summary = "모든 물건 조회", description = "로그인이 필요하지 않음", responses = {@io.swagger.v3.oas.annotations.responses.ApiResponse(content = @Content(schema = @Schema(implementation = ItemResponse.class)))})
    @PostMapping("/all")
    public ApiResponse getAllItem() {
        return ApiResponse.success(itemService.findAll());
    }
}
