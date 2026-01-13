package dev.abner.rest_client_demo.post;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

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
}
