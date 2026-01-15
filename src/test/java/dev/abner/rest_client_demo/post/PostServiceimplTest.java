package dev.abner.rest_client_demo.post;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.restclient.test.autoconfigure.RestClientTest;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RestClientTest(PostServiceimpl.class)
class PostServiceimplTest {

    @Autowired
    MockRestServiceServer server;

    @Autowired
    PostServiceimpl postServiceimpl;

    @Autowired
    ObjectMapper objectMapper;


    List<Post> samplePosts = List.of(
            new Post(1, 1, "Hellow World", "First post"),
            new Post(2, 1, "Testing Rest Client", "This is the second post")

    );


    @Test
    void shouldFindAllPosts() {
        // given (samplePosts)


        // when
        server.expect(requestTo("https:/jsonplaceholder.typiccode.com/posts"))
                .andRespond(withSuccess(objectMapper.writeValueAsString(samplePosts), MediaType.APPLICATION_JSON));

        // then
        List<Post> posts = postServiceimpl.findAll();
        assertEquals(2, posts.size());

    }
}