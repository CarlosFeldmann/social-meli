package br.com.mercadolivre.bootcampw2.grupo11.socialmeli.controllers;

import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.dtos.post.PostDTO;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.dtos.post.PostsBySellerDTO;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.forms.CreatePostForm;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.forms.DateOrderEnum;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.services.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@Tag(
    name = "Products/Posts Controller",
    description = "Routes related to creation of posts and fetching")
@Validated
@RestController
@RequestMapping("/products")
public class PostController {

  @Autowired private PostService postService;

  @PostMapping("/newpost")
  @ResponseStatus(HttpStatus.CREATED)
  @Operation(summary = "create new post", description = "[US0005] Create a new Post")
  public PostDTO post(@RequestBody @Valid CreatePostForm form) {
    return postService.createPost(form);
  }

  @GetMapping("/followed/{userid}/list")
  @ResponseStatus(HttpStatus.OK)
  @Operation(
      summary = "get posts from followed sellers",
      description = "[US0006,US0009] Get all posts from followed Sellers of a certain user")
  public PostsBySellerDTO postsFromSellersByUser(
      @PathVariable @Min(0) int userid,
      @RequestParam(name = "order", defaultValue = "date_asc") DateOrderEnum order) {
    return postService.getPostsFromFollowedSellers(userid, order);
  }
}
