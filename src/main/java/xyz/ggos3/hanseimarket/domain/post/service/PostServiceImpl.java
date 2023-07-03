package xyz.ggos3.hanseimarket.domain.post.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.ggos3.hanseimarket.domain.post.domain.Post;
import xyz.ggos3.hanseimarket.domain.post.domain.PostRepository;
import xyz.ggos3.hanseimarket.domain.post.comment.CommentRepository;
import xyz.ggos3.hanseimarket.domain.user.auth.domain.AuthUser;
import xyz.ggos3.hanseimarket.domain.post.dto.request.PostBodyUpdateRequest;
import xyz.ggos3.hanseimarket.domain.post.dto.request.PostNameUpdateRequest;
import xyz.ggos3.hanseimarket.domain.post.dto.request.PostSaveRequest;
import xyz.ggos3.hanseimarket.domain.user.auth.service.AuthUserImpl;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final AuthUserImpl authUserService;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;


    @Override
    @Transactional
    public Post findPostById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시물 입니다."));
    }

    @Override
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

    @Override
    @Transactional
    public void updatePostName(String userId, PostNameUpdateRequest request) {
        Post post = isOwner(userId, request.postId());
        postRepository.updatePostName(post.getId(), request.postName());
    }

    @Override
    @Transactional
    public void updatePostBody(String userId, PostBodyUpdateRequest request) {
        Post post = isOwner(userId, request.postId());
        postRepository.updatePostBody(post.getId(), request.postBody());
    }

    @Override
    @Transactional
    public void deletePost(String userId, Long postId) {
        Post post = isOwner(userId, postId);
        postRepository.delete(post);
    }

    private Post isOwner(String uuid, Long postId) {
        authUserService.findByUuid(uuid);
        Post post = findPostById(postId);

        if (!post.getAuthUser().getUuid().toString().equals(uuid))
            throw new IllegalArgumentException("게시글 수정, 삭제는 본인만 할 수 있습니다");
        return post;
    }

    @Override
    @Transactional
    public void clearAll() {
        commentRepository.deleteAll();
        postRepository.deleteAll();
    }
}
