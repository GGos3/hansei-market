package xyz.ggos3.hanseimarket.service.item;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import xyz.ggos3.hanseimarket.domain.item.Item;
import xyz.ggos3.hanseimarket.domain.item.ItemRepository;
import xyz.ggos3.hanseimarket.domain.user.UserRepository;
import xyz.ggos3.hanseimarket.domain.user.login.LoginUserRepository;
import xyz.ggos3.hanseimarket.dto.item.request.ItemSaveRequest;
import xyz.ggos3.hanseimarket.dto.item.request.ItemUpdateRequest;
import xyz.ggos3.hanseimarket.dto.user.request.UserCreateRequest;
import xyz.ggos3.hanseimarket.service.user.UserService;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ItemServiceTest {
    private final ItemService itemService;
    private final UserService userService;
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final LoginUserRepository loginUserRepository;

    @Autowired
    public ItemServiceTest(ItemService itemService, UserService userService, ItemRepository itemRepository, UserRepository userRepository, LoginUserRepository loginUserRepository) {
        this.itemService = itemService;
        this.userService = userService;
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
        this.loginUserRepository = loginUserRepository;
    }


    @AfterEach
    void clear() {
        loginUserRepository.deleteAll();
        itemRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    @DisplayName("item 생성")
    void createItemTest() {
        ItemSaveRequest itemSaveRequest = new ItemSaveRequest("test123", "가방", 1000,"2023년도에 구매한 가방입니다");
        UserCreateRequest userCreateRequest = new UserCreateRequest("test123", "testPassword", "테스트유저", "H1231", "01011111111");
        userService.createAccount(userCreateRequest);

        Item item = itemService.saveItem(itemSaveRequest);

        assertThat(item.getUser().getUserId()).isEqualTo(itemSaveRequest.getUserId());
        assertThat(item.getItemName()).isEqualTo(itemSaveRequest.getItemName());
        assertThat(item.getPrice()).isEqualTo(itemSaveRequest.getPrice());
        assertThat(item.getDescription()).isEqualTo(itemSaveRequest.getDescription());
    }

    @Test
    @DisplayName("조회수 카운트")
    void itemViewCountTest() {
        UserCreateRequest userCreateRequest = new UserCreateRequest("test123", "testPassword", "테스트유저", "H1231", "01011111111");
        ItemSaveRequest itemSaveRequest = new ItemSaveRequest("test123", "가방", 1000,"2023년도에 구매한 가방입니다");
        userService.createAccount(userCreateRequest);
        Item item = itemService.saveItem(itemSaveRequest);

        Item findItem = itemService.findItemById(item.getId());

        assertThat(findItem.getView()).isEqualTo(1);
    }

    @Test
    @DisplayName("상품 수정")
    void updateItemTest() {
        UserCreateRequest userCreateRequest = new UserCreateRequest("test123", "testPassword", "테스트유저", "H1231", "01011111111");
        ItemSaveRequest itemSaveRequest = new ItemSaveRequest("test123", "가방", 1000,"2023년도에 구매한 가방입니다");
        userService.createAccount(userCreateRequest);
        Item item = itemService.saveItem(itemSaveRequest);
        ItemUpdateRequest itemUpdateRequest = new ItemUpdateRequest(item.getId(), "test123", "모자", 1500, "멋진 모자!");

        itemService.updateItem(itemUpdateRequest);

        assertThat(itemService.findItemById(item.getId()).getItemName()).isEqualTo("모자");
    }
}