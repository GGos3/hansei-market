package xyz.ggos3.hanseimarket.domain.post.service;

import org.springframework.stereotype.Service;
import xyz.ggos3.hanseimarket.domain.post.domain.Post;
import xyz.ggos3.hanseimarket.domain.post.dto.request.PostBodyUpdateRequest;
import xyz.ggos3.hanseimarket.domain.post.dto.request.PostNameUpdateRequest;
import xyz.ggos3.hanseimarket.domain.post.dto.request.PostSaveRequest;

@Service
public interface PostService {
    Post findPostById(Long id);

    Post savePost(String uuid, PostSaveRequest request);

    void updatePostName(String uuid, PostNameUpdateRequest request);

    void updatePostBody(String uuid, PostBodyUpdateRequest request);

    void deletePost(String uuid, Long id);

    void clearAll();
}
