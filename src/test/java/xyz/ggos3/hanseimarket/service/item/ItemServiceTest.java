package xyz.ggos3.hanseimarket.service.item;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import xyz.ggos3.hanseimarket.domain.item.Item;
import xyz.ggos3.hanseimarket.domain.item.ItemStatus;
import xyz.ggos3.hanseimarket.domain.user.User;
import xyz.ggos3.hanseimarket.domain.user.auth.AuthUser;
import xyz.ggos3.hanseimarket.dto.item.request.ItemSaveRequest;
import xyz.ggos3.hanseimarket.dto.item.request.ItemStatusUpdateRequest;
import xyz.ggos3.hanseimarket.dto.item.request.ItemUpdateRequest;
import xyz.ggos3.hanseimarket.dto.item.response.ItemResponse;
import xyz.ggos3.hanseimarket.dto.user.auth.request.SignUpRequest;
import xyz.ggos3.hanseimarket.service.user.UserService;
import xyz.ggos3.hanseimarket.service.user.auth.AuthUserService;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ItemServiceTest {

    private final ItemService itemService;
    private final UserService userService;
    private final AuthUserService authUserService;

    @Autowired
    public ItemServiceTest(ItemService itemService, UserService userService, AuthUserService authUserService) {
        this.itemService = itemService;
        this.userService = userService;
        this.authUserService = authUserService;
    }


    @AfterEach
    void clear() {
        itemService.clearAll();
        userService.clearAll();
    }

    @Test
    @DisplayName("item 생성")
    void createItemTest() {
        // given
        ItemSaveRequest itemSaveRequest = new ItemSaveRequest("가방", 1000,"2023년도에 구매한 가방입니다");
        SignUpRequest signUpRequest = new SignUpRequest("test123", "testPassword", "테스트유저", "H1231", "01011111111");
        User user = userService.createAccount(signUpRequest);
        AuthUser authUser = authUserService.findByUserId(user.getUserId());

        // when
        Item item = itemService.saveItem(authUser.getUuid().toString(), itemSaveRequest);


        // then
        assertThat(item.getUser().getUserId()).isEqualTo(user.getUserId());
        assertThat(item.getItemName()).isEqualTo(itemSaveRequest.itemName());
        assertThat(item.getPrice()).isEqualTo(itemSaveRequest.price());
        assertThat(item.getDescription()).isEqualTo(itemSaveRequest.description());
    }

    @Test
    @DisplayName("조회수 카운트")
    void itemViewCountTest() {
        // given
        SignUpRequest signUpRequest = new SignUpRequest("test123", "testPassword", "테스트유저", "H1231", "01011111111");
        ItemSaveRequest itemSaveRequest = new ItemSaveRequest("가방", 1000,"2023년도에 구매한 가방입니다");
        User user = userService.createAccount(signUpRequest);
        AuthUser authUser = authUserService.findByUserId(user.getUserId());
        Item item = itemService.saveItem(authUser.getUuid().toString(), itemSaveRequest);

        // when
        ItemResponse itemResponse = itemService.getItem(item.getId());

        // then
        assertThat(itemResponse.view()).isEqualTo(1);
    }

    @Test
    @DisplayName("상품 상태 변경")
    void updateStatusTest() {
        // given
        SignUpRequest signUpRequest = new SignUpRequest("test123", "testPassword", "테스트유저", "H1231", "010111111111");
        ItemSaveRequest itemSaveRequest = new ItemSaveRequest("양말", 30000, "나이키 로고가 반대로 되어있는 중국산 나이크 양말");

        User user = userService.createAccount(signUpRequest);
        AuthUser authUser = authUserService.findByUserId(user.getUserId());
        Item item = itemService.saveItem(authUser.getUuid().toString(), itemSaveRequest);

        ItemStatusUpdateRequest itemStatusUpdateRequest = new ItemStatusUpdateRequest(ItemStatus.거래완료);

        // when
        itemService.updateStatus(authUser.getUuid().toString(), item.getId(), itemStatusUpdateRequest);

        // then
        assertThat(itemService.findItemById(item.getId()).getStatus()).isEqualTo(ItemStatus.거래완료);
    }

    @Test
    @DisplayName("상품 수정")
    void updateItemTest() {
        SignUpRequest signUpRequest = new SignUpRequest("test123", "testPassword", "테스트유저", "H1231", "01011111111");
        ItemSaveRequest itemSaveRequest = new ItemSaveRequest("가방", 1000,"2023년도에 구매한 가방입니다");
        User user = userService.createAccount(signUpRequest);
        AuthUser authUser = authUserService.findByUserId(user.getUserId());
        Item item = itemService.saveItem(authUser.getUuid().toString(), itemSaveRequest);
        ItemUpdateRequest itemUpdateRequest = new ItemUpdateRequest("test123", "모자", 1500, "멋진 모자!");

        itemService.updateItem(authUser.getUuid().toString(), item.getId(), itemUpdateRequest);

        assertThat(itemService.findItemById(item.getId()).getItemName()).isEqualTo("모자");
    }
}