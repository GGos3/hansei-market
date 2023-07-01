package xyz.ggos3.hanseimarket.service.post;

import org.springframework.stereotype.Service;
import xyz.ggos3.hanseimarket.domain.post.Post;
import xyz.ggos3.hanseimarket.dto.post.request.PostBodyUpdateRequest;
import xyz.ggos3.hanseimarket.dto.post.request.PostNameUpdateRequest;
import xyz.ggos3.hanseimarket.dto.post.request.PostSaveRequest;

@Service
public interface PostService {
    Post findPostById(Long id);

    Post savePost(String uuid, PostSaveRequest request);

    void updatePostName(String uuid, PostNameUpdateRequest request);

    void updatePostBody(String uuid, PostBodyUpdateRequest request);

    void deletePost(String uuid, Long id);

    void clearAll();
}
