package xyz.ggos3.hanseimarket.controller.item;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import xyz.ggos3.hanseimarket.domain.item.Item;
import xyz.ggos3.hanseimarket.dto.item.request.ItemSaveRequest;
import xyz.ggos3.hanseimarket.service.item.ItemService;

@RestController
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @PostMapping("/item/save")
    public void createItem(@RequestBody ItemSaveRequest request) {
        itemService.saveItem(request);
    }

    @GetMapping("/item/{id}")
    public Item getItem(@PathVariable Long id) {
        return itemService.findItemById(id);
    }
}
