package xyz.ggos3.hanseimarket.domain.post.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import xyz.ggos3.hanseimarket.common.dto.ApiResponse;
import xyz.ggos3.hanseimarket.common.security.annotation.UserAuthorize;
import xyz.ggos3.hanseimarket.domain.post.comment.dto.response.CommentResponse;
import xyz.ggos3.hanseimarket.domain.post.comment.service.CommentService;
import xyz.ggos3.hanseimarket.domain.post.domain.Post;
import xyz.ggos3.hanseimarket.domain.post.dto.request.PostSaveRequest;
import xyz.ggos3.hanseimarket.domain.post.dto.response.PostResponse;
import xyz.ggos3.hanseimarket.domain.post.service.PostService;
import xyz.ggos3.hanseimarket.domain.user.domain.User;

import java.util.List;

@Tag(name = "게시글 관련 API")
@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final CommentService commentService;

    @GetMapping("/post/{id}")
    public ApiResponse<PostResponse> getPost(@PathVariable Long id) {
        Post post = postService.findPostById(id);
        List<CommentResponse> comments = commentService.getCommentsByPost(post);

        return ApiResponse.success(
                PostResponse.builder()
                        .userName(post.getAuthUser().getUserId())
                        .postId(post.getId())
                        .postBody(post.getPostBody())
                        .postComments(comments)
                        .createDate(post.getCreateDate())
                        .build()
        );
    }

    @UserAuthorize
    @PostMapping("/post/new")
    public ApiResponse<String> createPost(@AuthenticationPrincipal User user, @RequestBody PostSaveRequest request) {
        return ApiResponse.success("게시글이 저장되었습니다.", request.postName());
    }
}
