package xyz.ggos3.hanseimarket.service.item.like;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import xyz.ggos3.hanseimarket.domain.item.Item;
import xyz.ggos3.hanseimarket.domain.item.like.LikedItem;
import xyz.ggos3.hanseimarket.domain.item.like.LikedItemRepository;
import xyz.ggos3.hanseimarket.domain.user.User;
import xyz.ggos3.hanseimarket.domain.user.auth.AuthUser;
import xyz.ggos3.hanseimarket.dto.item.like.request.AddLikeItemRequest;
import xyz.ggos3.hanseimarket.dto.item.request.ItemSaveRequest;
import xyz.ggos3.hanseimarket.dto.user.auth.request.SignUpRequest;
import xyz.ggos3.hanseimarket.service.item.ItemService;
import xyz.ggos3.hanseimarket.service.user.UserService;
import xyz.ggos3.hanseimarket.service.user.auth.AuthUserService;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
class LikedItemServiceTest {
    private final ItemService itemService;
    private final UserService userService;
    private final AuthUserService authUserService;
    private final LikedItemService likedItemService;
    private final LikedItemRepository likedItemRepository;

    @Autowired
    public LikedItemServiceTest(LikedItemService likedItemService, UserService userService, ItemService itemService, LikedItemRepository likedItemRepository, AuthUserService authUserService) {
        this.itemService = itemService;
        this.userService = userService;
        this.authUserService = authUserService;
        this.likedItemService = likedItemService;
        this.likedItemRepository = likedItemRepository;
    }

    @AfterEach
    void clear() {
        itemService.clearAll();
        userService.clearAll();
    }

    @Test
    @DisplayName("찜 리스트 생성")
    void addLikedItemTest() {
        //give
        SignUpRequest signUpRequest = new SignUpRequest("test123", "testPassword", "테스트유저", "H1231", "01011111111");
        ItemSaveRequest itemSaveRequest = new ItemSaveRequest("가방", 1000,"2023년도에 구매한 가방입니다");
        User user = userService.createAccount(signUpRequest);
        AuthUser authUser = authUserService.findByUserId(user.getUserId());
        Item item = itemService.saveItem(authUser.getUuid().toString(), itemSaveRequest);
        AddLikeItemRequest request = new AddLikeItemRequest(item.getId());

        //when
        LikedItem likedItem = likedItemService.addLikedItem(authUser.getUuid().toString(), request);

        //then
        assertThat(likedItemRepository.findById(likedItem.getId()).get().getUser().getUserId()).isEqualTo("test123");
        assertThat(likedItemRepository.findById(likedItem.getId()).get().getItem().getItemName()).isEqualTo("가방");
    }

    @Test
    @DisplayName("좋아요 증가")
    void increaseLikeTest() {
        SignUpRequest signUpRequest = new SignUpRequest("test123", "testPassword", "테스트유저", "H1231", "01011111111");
        ItemSaveRequest itemSaveRequest = new ItemSaveRequest("가방", 1000,"2023년도에 구매한 가방입니다");
        User user = userService.createAccount(signUpRequest);
        AuthUser authUser = authUserService.findByUserId(user.getUserId());
        Item item = itemService.saveItem(authUser.getUuid().toString(), itemSaveRequest);
        AddLikeItemRequest request = new AddLikeItemRequest(item.getId());

        LikedItem likedItem = likedItemService.addLikedItem(authUser.getUuid().toString(), request);

        assertThat(itemService.findItemById(item.getId()).getLikeCount()).isEqualTo(1);
    }
}