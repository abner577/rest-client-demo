package dev.abner.rest_client_demo.post;

import dev.abner.rest_client_demo.post.exception.PostNotFoundException;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.rmi.ServerException;
import java.util.List;

@Service
public class PostServiceimpl implements PostService {

    private final RestClient restClient;

    public PostServiceimpl(RestClient restClient) {
        this.restClient = restClient;
    }

    @Override
    public List<Post> findAll() {
        return restClient.get()
                .uri("/posts")
                .retrieve()
                .body(new ParameterizedTypeReference<List<Post>>() {});
    }

    @Override
    public Post findById(Integer id) {
        return restClient.get()
                .uri("/posts/{id}", id)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (req, response) -> {
                    throw new PostNotFoundException("Post with an id of: " + id + " doesnt exist");
                })
                .onStatus(HttpStatusCode::is5xxServerError, (req, res) -> {
                    throw new ServerException("JSONPlaceholder error: " + res.getStatusCode());
                })
                .body(Post.class);
    }

    @Override
    public Post createPost(Post post) {
        return restClient.post()
                .uri("/posts")
                .contentType(MediaType.APPLICATION_JSON)
                .body(post)
                .retrieve()
                // Service doesn't force you to send all fields, this is just hypothetical
                .onStatus(HttpStatusCode::is4xxClientError, (req, response) -> {;
                    throw new IllegalArgumentException("Post must have all of the following fields:" +
                            "Integer id, Integer userId, String title, String body");
                })
                .onStatus(HttpStatusCode::is5xxServerError, (req, res) -> {
                    throw new ServerException("JSONPlaceholder error: " + res.getStatusCode());
                })
                .body(Post.class);
    }

    @Override
    public Post updatePost(Post post, Integer id) {
        return restClient.put()
                .uri("/posts/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .body(post)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (req, response) -> {;
                    throw new IllegalArgumentException("Post must have all of the following fields:" +
                            "Integer id, Integer userId, String title, String body");
                })
                .onStatus(HttpStatusCode::is5xxServerError, (req, res) -> {
                    throw new ServerException("JSONPlaceholder error: " + res.getStatusCode());
                })
                .body(Post.class);
    }

    @Override
    public void deletePost(Integer id) {
        restClient.delete()
                .uri("/posts/{id}", id)
                .retrieve()
                // Service always returns 200 even when the id passed doesnt exist, again just hypothetical
                .onStatus(HttpStatusCode::is4xxClientError, (req, response) -> {
                    throw new PostNotFoundException("Post with an id of: " + id + " doesnt exist");
                })
                .onStatus(HttpStatusCode::is5xxServerError, (req, res) -> {
                    throw new ServerException("JSONPlaceholder error: " + res.getStatusCode());
                })
                .toBodilessEntity(); // Returns a ResponseEntity without a body
    }


}
