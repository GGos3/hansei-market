package xyz.ggos3.hanseimarket.controller.item;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.ggos3.hanseimarket.domain.item.Item;
import xyz.ggos3.hanseimarket.dto.item.request.ItemSaveRequest;
import xyz.ggos3.hanseimarket.dto.item.request.ItemStatusUpdateRequest;
import xyz.ggos3.hanseimarket.service.item.ItemService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @PostMapping("/item/save")
    public ResponseEntity<Item> createItem(@RequestBody ItemSaveRequest request) {
        itemService.saveItem(request);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/item/{id}")
    public Item getItem(@PathVariable Long id) {
        return itemService.findItemById(id);
    }

    @PatchMapping("/item/{id}/like")
    public Item increaseLike(@PathVariable Long id) {
        Item item = itemService.findItemById(id);
        item.increaseLike();

        return item;
    }

    @PatchMapping("/item/{id}/status")
    public Item updateItemStatus(@PathVariable Long id, ItemStatusUpdateRequest request) {
        return itemService.updateStatus(request);
    }

    @PostMapping("/item/all")
    public List<Item> getAllItem() {
        return itemService.findAll();
    }
}
