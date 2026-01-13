package dev.abner.rest_client_demo.post;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostServiceimpl postServiceimpl;

    public PostController(PostServiceimpl postServiceimpl) {
        this.postServiceimpl = postServiceimpl;
    }


    @GetMapping
     List<Post> findAllPosts() {
       List<Post> list = postServiceimpl.findAll();
       return list;
    }
}
