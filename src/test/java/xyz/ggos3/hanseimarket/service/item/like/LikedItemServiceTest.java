package xyz.ggos3.hanseimarket.service.item.like;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import xyz.ggos3.hanseimarket.domain.item.Item;
import xyz.ggos3.hanseimarket.domain.item.ItemRepository;
import xyz.ggos3.hanseimarket.domain.item.like.LikedItem;
import xyz.ggos3.hanseimarket.domain.item.like.LikedItemRepository;
import xyz.ggos3.hanseimarket.domain.user.User;
import xyz.ggos3.hanseimarket.domain.user.UserRepository;
import xyz.ggos3.hanseimarket.domain.user.auth.AuthUserRepository;
import xyz.ggos3.hanseimarket.dto.item.like.request.AddLikeItemRequest;
import xyz.ggos3.hanseimarket.dto.item.request.ItemSaveRequest;
import xyz.ggos3.hanseimarket.dto.user.auth.request.SignUpRequest;
import xyz.ggos3.hanseimarket.service.item.ItemService;
import xyz.ggos3.hanseimarket.service.user.UserService;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
class LikedItemServiceTest {
    private final LikedItemService likedItemService;
    private final UserService userService;
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final ItemService itemService;
    private final LikedItemRepository likedItemRepository;
    private final AuthUserRepository authUserRepository;

    @Autowired
    public LikedItemServiceTest(LikedItemService likedItemService, UserService userService, ItemRepository itemRepository, UserRepository userRepository, ItemService itemService, LikedItemRepository likedItemRepository, AuthUserRepository authUserRepository) {
        this.likedItemService = likedItemService;
        this.userService = userService;
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
        this.itemService = itemService;
        this.likedItemRepository = likedItemRepository;
        this.authUserRepository = authUserRepository;
    }

    @AfterEach
    void clear() {
        authUserRepository.deleteAll();
        likedItemRepository.deleteAll();
        itemRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    @DisplayName("찜 리스트 생성")
    void addLikedItemTest() {
        //give
        SignUpRequest signUpRequest = new SignUpRequest("test123", "testPassword", "테스트유저", "H1231", "01011111111");
        ItemSaveRequest itemSaveRequest = new ItemSaveRequest("가방", 1000,"2023년도에 구매한 가방입니다");
        User user = userService.createAccount(signUpRequest);
        Item item = itemService.saveItem(user.getUserId(), itemSaveRequest);
        AddLikeItemRequest request = new AddLikeItemRequest(item.getId());

        //when
        LikedItem likedItem = likedItemService.addLikedItem(user.getUserId(), request);

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
        Item item = itemService.saveItem(user.getUserId(), itemSaveRequest);
        AddLikeItemRequest request = new AddLikeItemRequest(item.getId());

        LikedItem likedItem = likedItemService.addLikedItem(user.getUserId(), request);

        assertThat(itemService.findItemById(item.getId()).getLikeCount()).isEqualTo(1);
    }
}