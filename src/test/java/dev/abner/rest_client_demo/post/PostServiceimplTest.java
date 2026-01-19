package dev.abner.rest_client_demo.post;

import dev.abner.rest_client_demo.RestClientDemoApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.restclient.test.autoconfigure.RestClientTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.HttpServerErrorException;
import tools.jackson.databind.ObjectMapper;

import java.rmi.ServerError;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.*;

@RestClientTest(PostServiceimpl.class)
class PostServiceImplTest {

    @Autowired
    MockRestServiceServer server;

    @Autowired
    PostServiceimpl postServiceimpl;

    @Autowired
    ObjectMapper objectMapper;


    Post post1 = new Post(1, 1, "Hello World", "First post");
    Post post2 = new Post(2, 1, "Testing Rest Client", "This is the second post");

    List<Post> samplePosts = List.of(post1, post2);


    @Test
    void shouldFindAllPosts() {
        // given (samplePosts)


        // when
        server.expect(requestTo("https://jsonplaceholder.typicode.com/posts"))
                .andRespond(withSuccess(objectMapper.writeValueAsString(samplePosts), MediaType.APPLICATION_JSON));

        // then
        List<Post> posts = postServiceimpl.findAll();
        assertEquals(2, posts.size());

        server.verify();
    }

    @Test
    void shouldFindPostByIdWhenValidIdIsGiven() {
        // given (samplePosts)

        // when
        server.expect(requestTo("https://jsonplaceholder.typicode.com/posts/1"))
                .andRespond(withSuccess(objectMapper.writeValueAsString(post1), MediaType.APPLICATION_JSON));

        // then
        Post post = postServiceimpl.findById(1);
        assertEquals(1, post.id());
        assertEquals("Hello World", post.title());

        server.verify();
    }

    @Test
    void shouldReturnStatus500WhenInvalidIdIsGiven() {
        // given (samplePosts)

        // when
        server.expect(requestTo("https://jsonplaceholder.typicode.com/posts/999"))
                .andRespond(withStatus(HttpStatus.NOT_FOUND));

        // then
        assertThrows(PostNotFoundException.class, () -> postServiceimpl.findById(999));

        server.verify();
    }

}