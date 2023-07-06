package xyz.ggos3.hanseimarket.domain.post.comment.service;

import org.springframework.stereotype.Service;
import xyz.ggos3.hanseimarket.domain.post.domain.Post;
import xyz.ggos3.hanseimarket.domain.post.comment.Comment;
import xyz.ggos3.hanseimarket.domain.post.comment.dto.request.CommentSaveRequest;
import xyz.ggos3.hanseimarket.domain.post.comment.dto.request.CommentUpdateRequest;
import xyz.ggos3.hanseimarket.domain.post.comment.dto.response.CommentResponse;

import java.util.List;

@Service
public interface CommentService {
    CommentResponse getComment(Long id);
    List<CommentResponse> getCommentsByPost(Post post);
    Comment findCommentById(Long id);
    List<Comment> findCommentByPost(Post post);

    Comment saveComment(String uuid, CommentSaveRequest request);

    Comment updateComment(String uuid, CommentUpdateRequest request);

    void deleteComment(String uuid, Long id);
}
