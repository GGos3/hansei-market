package xyz.ggos3.hanseimarket.service.item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.ggos3.hanseimarket.domain.item.Item;
import xyz.ggos3.hanseimarket.domain.item.ItemRepository;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public void saveItem() {

    }

    public Item findItemByName(String itemName) {
        return itemRepository.findByItemName(itemName)
                .orElseThrow(() -> new IllegalArgumentException("이름에 맞는 item이 없습니다."));
    }

    public Item findItemById(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("id에 맞는 item이 없습니다."));
    }
}
