package dev.abner.rest_client_demo.post;

import java.util.List;

public interface PostService {
    List<Post> findAll();
    Post findById(Integer id);
    Post createPost(Post post);
    Post updatePost(Post post, Integer id);
    void deletePost(Integer id);
}
