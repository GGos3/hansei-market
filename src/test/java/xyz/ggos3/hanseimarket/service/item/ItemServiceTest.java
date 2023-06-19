package xyz.ggos3.hanseimarket.service.item;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import xyz.ggos3.hanseimarket.domain.item.ItemRepository;
import xyz.ggos3.hanseimarket.domain.user.UserRepository;
import xyz.ggos3.hanseimarket.domain.user.login.LoginUserRepository;
import xyz.ggos3.hanseimarket.dto.item.request.ItemSaveRequest;
import xyz.ggos3.hanseimarket.dto.item.request.ItemUpdateRequest;
import xyz.ggos3.hanseimarket.dto.user.request.UserCreateRequest;
import xyz.ggos3.hanseimarket.service.user.UserService;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
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

    @BeforeEach
    void createUser() {
        UserCreateRequest userCreateRequest = new UserCreateRequest("test123", "testPassword", "테스트유저", "H1231", "01011111111");
        userService.createAccount(userCreateRequest);
    }

    @AfterEach
    void clear() {
        loginUserRepository.deleteAll();
        itemRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    @Order(1)
    @DisplayName("item 생성")
    void createItemTest() {
        ItemSaveRequest itemSaveRequest = new ItemSaveRequest("test123", "가방", 1000,"2023년도에 구매한 가방입니다");

        itemService.saveItem(itemSaveRequest);

        assertThat(itemRepository.findAll().stream()
                .filter(item -> item.getItemName().equals("가방"))
                .findFirst().get().getItemName()).isEqualTo("가방");
    }

    @Test
    @Order(2)
    @DisplayName("조회수 카운트")
    void itemViewCountTest() {
        ItemSaveRequest itemSaveRequest = new ItemSaveRequest("test123", "가방", 1000,"2023년도에 구매한 가방입니다");

        itemService.saveItem(itemSaveRequest);

        itemService.findItemById(2L);

        assertThat(itemRepository.findAll().get(0).getView()).isEqualTo(1);
    }

    @Test
    @Order(3)
    @DisplayName("상품 수정")
    void updateItemTest() {
        ItemSaveRequest itemSaveRequest = new ItemSaveRequest("test123", "가방", 1000,"2023년도에 구매한 가방입니다");
        ItemUpdateRequest itemUpdateRequest = new ItemUpdateRequest(3L, "test123", "모자", 1500, "멋진 모자!");

        itemService.saveItem(itemSaveRequest);
        itemService.updateItem(itemUpdateRequest);

        assertThat(itemService.findItemById(3L).getItemName()).isEqualTo("모자");
    }
}