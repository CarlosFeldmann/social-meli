package br.com.mercadolivre.bootcampw2.grupo11.socialmeli.controllers;

import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.dtos.PromotionalQuantityBySellerDTO;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.dtos.promotional.ListPromotionalPostsBySellerDTO;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.dtos.promotional.PromotionalPostDTO;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.forms.CreatePromocionalPostForm;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.services.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@Tag(name = "Promotional Post Controller", description = "Responsible for creating promotional posts and fetch them")
@RestController
@RequestMapping("/products")
public class PromotionalPostController {

    private PostService postService;

    @Autowired
    public PromotionalPostController(PostService postService) {
        this.postService = postService;
    }

    @Operation(description = "Create a promotional post", summary = "create a promotional post")
    @PostMapping("/newpromopost")
    @ResponseStatus(HttpStatus.CREATED)
    public PromotionalPostDTO createPromotionalPost(@Valid @RequestBody CreatePromocionalPostForm form) {
        return postService.createPromotionalPost(form);
    }


    @Operation(description = "Get promotional posts count for a given seller", summary = "promotional post count by seller")
    @PostMapping("/{userId}/countPromo/")
    @ResponseStatus(HttpStatus.OK)
    public PromotionalQuantityBySellerDTO getPromotionalPostCountBySeller(@PathVariable(name = "userId") @Min(0) Integer sellerId) {
        return postService.getPromotionalPostsCountBySeller(sellerId);
    }

    @Operation(description = "Get promotional posts from a given seller", summary = "promotional posts from seller")
    @GetMapping("/{userId}/list/")
    @ResponseStatus(HttpStatus.OK)
    public ListPromotionalPostsBySellerDTO getPromotionalPostsBySeller(@PathVariable(name = "userId") @Min(0) Integer sellerId) {
        return postService.getPromotionalPostsBySeller(sellerId);
    }

}
