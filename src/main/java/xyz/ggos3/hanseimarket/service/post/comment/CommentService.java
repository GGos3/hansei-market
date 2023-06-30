package xyz.ggos3.hanseimarket.service.post.comment;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.ggos3.hanseimarket.domain.post.Post;
import xyz.ggos3.hanseimarket.domain.post.comment.Comment;
import xyz.ggos3.hanseimarket.domain.post.comment.CommentRepository;
import xyz.ggos3.hanseimarket.domain.user.auth.AuthUser;
import xyz.ggos3.hanseimarket.dto.post.comment.request.CommentSaveRequest;
import xyz.ggos3.hanseimarket.dto.post.comment.request.CommentUpdateRequest;
import xyz.ggos3.hanseimarket.dto.post.comment.response.CommentResponse;
import xyz.ggos3.hanseimarket.service.post.PostService;
import xyz.ggos3.hanseimarket.service.user.auth.AuthUserService;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final AuthUserService authUserService;
    private final CommentRepository commentRepository;
    private final PostService postService;

    @Transactional
    public CommentResponse getComment(Long id) {
        Comment comment = findCommentById(id);
        return new CommentResponse(
                comment.getAuthUser().getUserId(),
                comment.getComment(),
                comment.getCreateDate()
        );
    }

    @Transactional
    public Comment findCommentById(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 댓글 입니다."));
    }

    @Transactional
    public Comment saveComment(String userId, CommentSaveRequest request) {
        AuthUser user = authUserService.findByUuid(userId);
        Post post = postService.findPostById(request.postId());

        Comment comment = new Comment(request.comment(), user, post);

        return commentRepository.save(comment);
    }

    @Transactional
    public Comment updateComment(String userId, CommentUpdateRequest request) {
        isOwner(userId, request.commentId());

        commentRepository.updateComment(request.commentId(), request.comment());

        return findCommentById(request.commentId());
    }

    @Transactional
    public void deleteComment(String userId, Long id) {
        Comment comment = isOwner(userId, id);

        commentRepository.delete(comment);
    }

    private Comment isOwner(String userId, Long id) {
        AuthUser user = authUserService.findByUuid(userId);
        Comment comment = findCommentById(id);

        if (!comment.getAuthUser().getUserId().equals(user.getUserId()))
            throw new IllegalArgumentException("댓글은 본인만 수정, 삭제할 수 있습니다.");

        return comment;
    }
}
