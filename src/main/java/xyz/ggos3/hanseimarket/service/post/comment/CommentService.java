package xyz.ggos3.hanseimarket.service.post.comment;

import org.springframework.stereotype.Service;
import xyz.ggos3.hanseimarket.domain.post.Post;
import xyz.ggos3.hanseimarket.domain.post.comment.Comment;
import xyz.ggos3.hanseimarket.dto.post.comment.request.CommentSaveRequest;
import xyz.ggos3.hanseimarket.dto.post.comment.request.CommentUpdateRequest;
import xyz.ggos3.hanseimarket.dto.post.comment.response.CommentResponse;

import java.util.List;

@Service
public interface CommentService {
    CommentResponse getComment(Long id);

    Comment findCommentById(Long id);
    List<Comment> findCommentByPost(Post post);

    Comment saveComment(String uuid, CommentSaveRequest request);

    Comment updateComment(String uuid, CommentUpdateRequest request);

    void deleteComment(String uuid, Long id);
}
