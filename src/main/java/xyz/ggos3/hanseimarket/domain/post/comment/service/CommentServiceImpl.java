package xyz.ggos3.hanseimarket.domain.post.comment.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.ggos3.hanseimarket.domain.post.comment.Comment;
import xyz.ggos3.hanseimarket.domain.post.comment.CommentRepository;
import xyz.ggos3.hanseimarket.domain.post.domain.Post;
import xyz.ggos3.hanseimarket.domain.user.auth.domain.AuthUser;
import xyz.ggos3.hanseimarket.domain.post.comment.dto.request.CommentSaveRequest;
import xyz.ggos3.hanseimarket.domain.post.comment.dto.request.CommentUpdateRequest;
import xyz.ggos3.hanseimarket.domain.post.comment.dto.response.CommentResponse;
import xyz.ggos3.hanseimarket.domain.post.service.PostService;
import xyz.ggos3.hanseimarket.domain.user.auth.service.AuthUserImpl;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final PostService postService;
    private final AuthUserImpl authUserService;
    private final CommentRepository commentRepository;

    @Override
    @Transactional
    public CommentResponse getComment(Long id) {
        Comment comment = findCommentById(id);
        return new CommentResponse(
                comment.getAuthUser().getUserId(),
                comment.getComment(),
                comment.getCreateDate()
        );
    }

    @Override
    @Transactional
    public List<CommentResponse> getCommentsByPost(Post post) {
        return findCommentByPost(post).stream()
                .map(CommentResponse::new)
                .toList();
    }


    @Override
    @Transactional
    public Comment findCommentById(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 댓글 입니다."));
    }

    @Override
    @Transactional
    public List<Comment> findCommentByPost(Post post) {
        return Optional.of(
                commentRepository.findByPost(post))
                .orElseThrow(() -> new IllegalArgumentException("댓글이 존재하지 않습니다."));
    }

    @Override
    @Transactional
    public Comment saveComment(String userId, CommentSaveRequest request) {
        AuthUser user = authUserService.findByUuid(userId);
        Post post = postService.findPostById(request.postId());

        Comment comment = new Comment(request.comment(), user, post);

        return commentRepository.save(comment);
    }

    @Override
    @Transactional
    public Comment updateComment(String uuid, CommentUpdateRequest request) {
        isOwner(uuid, request.commentId());

        commentRepository.updateComment(request.commentId(), request.comment());

        return findCommentById(request.commentId());
    }

    @Override
    @Transactional
    public void deleteComment(String uuid, Long id) {
        Comment comment = isOwner(uuid, id);

        commentRepository.delete(comment);
    }

    private Comment isOwner(String uuid, Long id) {
        AuthUser user = authUserService.findByUuid(uuid);
        Comment comment = findCommentById(id);

        if (!comment.getAuthUser().getUserId().equals(user.getUserId()))
            throw new IllegalArgumentException("댓글은 본인만 수정, 삭제할 수 있습니다.");

        return comment;
    }
}
