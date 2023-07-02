package xyz.ggos3.hanseimarket.service.post;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import xyz.ggos3.hanseimarket.domain.post.Post;
import xyz.ggos3.hanseimarket.domain.user.User;
import xyz.ggos3.hanseimarket.domain.user.auth.AuthUser;
import xyz.ggos3.hanseimarket.dto.post.request.PostNameUpdateRequest;
import xyz.ggos3.hanseimarket.dto.post.request.PostSaveRequest;
import xyz.ggos3.hanseimarket.dto.user.auth.request.SignUpRequest;
import xyz.ggos3.hanseimarket.service.item.ItemService;
import xyz.ggos3.hanseimarket.service.user.UserService;
import xyz.ggos3.hanseimarket.service.user.auth.AuthUserService;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class PostServiceTest {

    private final PostService postService;
    private final UserService userService;
    private final ItemService itemService;
    private final AuthUserService authUserService;

    @Autowired
    public PostServiceTest(PostService postService, UserService userService, ItemService itemService, AuthUserService authUserService) {
        this.postService = postService;
        this.userService = userService;
        this.itemService = itemService;
        this.authUserService = authUserService;
    }

    @AfterEach
    void clear() {
        postService.clearAll();
        itemService.clearAll();
        userService.clearAll();
    }

    @Test
    @DisplayName("게시글 저장")
    void createPostTest() {
        // given
        User user = userService.createAccount(new SignUpRequest("test123", "testPassword", "테스트", "H1111", "01011111111"));
        AuthUser authUser = authUserService.findByUserId("test123");
        PostSaveRequest request = new PostSaveRequest("오늘 12시에 밥드실분 있나요?", "청년 떡볶이 먹고 싶은데 2~3인 분이라 혼자먹기 힘들어서 모집해 봅니다.");

        // when
        Post post = postService.savePost(authUser.getUuid().toString(), request);

        // then
        assertThat(post.getPostName()).isEqualTo(request.postName());
        assertThat(post.getPostBody()).isEqualTo(request.postBody());
    }

    @Test
    @DisplayName("게시글 제목 수정")
    void updatePostTest() {
        // given
        userService.createAccount(new SignUpRequest("test123", "tsetPassword", "테스트", "H1111", "01011111111"));
        AuthUser authUser = authUserService.findByUserId("test123");
        Post post = postService.savePost(
                authUser.getUuid().toString(),
                new PostSaveRequest("스파이더맨 어떤가요?", "스파이더맨 보려고 하는데 어떤가요?")
        );

        // when
        postService.updatePostName(
                authUser.getUuid().toString(),
                new PostNameUpdateRequest(post.getId(), "스파이더맨 어떤가요?(스포 ㄴㄴ)")
        );

        // then
        assertThat(postService.findPostById(post.getId()).getPostName()).isEqualTo("스파이더맨 어떤가요?(스포 ㄴㄴ)");
    }
}