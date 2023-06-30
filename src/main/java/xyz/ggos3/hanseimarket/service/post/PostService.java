package xyz.ggos3.hanseimarket.service.post;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.ggos3.hanseimarket.domain.post.Post;
import xyz.ggos3.hanseimarket.domain.post.PostRepository;
import xyz.ggos3.hanseimarket.domain.user.auth.AuthUser;
import xyz.ggos3.hanseimarket.dto.post.request.PostSaveRequest;
import xyz.ggos3.hanseimarket.dto.post.response.PostResponse;
import xyz.ggos3.hanseimarket.service.user.auth.AuthUserService;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final AuthUserService authUserService;

    public PostResponse getPost(Long id) {
        return new PostResponse(findPostById(id));
    }

    @Transactional
    public Post findPostById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시물 입니다."));
    }

    @Transactional
    public Post savePost(String userId, PostSaveRequest request) {
        AuthUser user = authUserService.findByUuid(userId);
        Post post = Post.builder()
                .user(user)
                .postName(request.postName())
                .postBody(request.postBody())
                .build();

        return postRepository.save(post);
    }
}
