package dev.abner.rest_client_demo.post;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostServiceimpl postServiceimpl;

    public PostController(PostServiceimpl postServiceimpl) {
        this.postServiceimpl = postServiceimpl;
    }


    @GetMapping
     public List<Post> findAllPosts() {
       List<Post> list = postServiceimpl.findAll();
       return list;
    }

    @GetMapping("/{id}")
    public Post findPost(@PathVariable Integer id) {
        return postServiceimpl.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Post createPost(@RequestBody Post post) {
        return postServiceimpl.createPost(post);
    }

    @PutMapping("/{id}")
    public Post updatePost(@RequestBody Post post, @PathVariable Integer id) {
        return postServiceimpl.updatePost(post, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(@PathVariable Integer id) {
        postServiceimpl.deletePost(id);
    }

}
