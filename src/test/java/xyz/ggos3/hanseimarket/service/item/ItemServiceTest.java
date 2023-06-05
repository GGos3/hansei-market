package xyz.ggos3.hanseimarket.service.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import xyz.ggos3.hanseimarket.domain.item.Item;
import xyz.ggos3.hanseimarket.domain.item.ItemRepository;
import xyz.ggos3.hanseimarket.dto.item.request.ItemSaveRequest;
import xyz.ggos3.hanseimarket.dto.user.request.UserCreateRequest;
import xyz.ggos3.hanseimarket.service.user.UserService;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class ItemServiceTest {
    private final ItemService itemService;
    private final UserService userService;
    private final ItemRepository itemRepository;

    @Autowired
    public ItemServiceTest(ItemService itemService, UserService userService, ItemRepository itemRepository) {
        this.itemService = itemService;
        this.userService = userService;
        this.itemRepository = itemRepository;
    }

    @Test
    @DisplayName("item 생성")
    void createItemTest() {
        ItemSaveRequest itemSaveRequest = new ItemSaveRequest("test123", "가방", 1000,"2023년도에 구매한 가방입니다");
        UserCreateRequest userCreateRequest = new UserCreateRequest("test123", "testPassword", "테스트유저", "H1231", "01011111111");

        userService.createAccount(userCreateRequest);
        itemService.saveItem(itemSaveRequest);

        Item itemByUserId = itemService.findItemByUserId("test123");
        assertThat(itemByUserId.getId()).isEqualTo(1L);
        assertThat(userService.findUserByUserId("test123").getUserItem().get(0).getId()).isEqualTo(itemByUserId.getId());
    }
}