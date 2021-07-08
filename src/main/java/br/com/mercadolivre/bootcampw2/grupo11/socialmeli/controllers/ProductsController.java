package br.com.mercadolivre.bootcampw2.grupo11.socialmeli.controllers;

import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.dtos.PostDTO;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.dtos.PostsBySellerDTO;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.entities.Post;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.forms.CreatePostForm;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.forms.DateOrderEnum;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.services.ProductsService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.util.List;

@Validated
@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductsService productsService;

    @ResponseStatus(HttpStatus.CREATED)
    @Operation(description="Create a new Post")
    @PostMapping("/newpost")
    public void post(@RequestBody CreatePostForm Postform) {
        productsService.createNewPost(Postform);
    }

    @Operation(description="Get all posts from followed Sellers of a certain user")
    @GetMapping("/followed/{userid}/list")
    @ResponseStatus(HttpStatus.OK)
    public PostsBySellerDTO postsFromSellersByUser(@PathVariable @Min(0) int userid,
                                                   @RequestParam(name = "order", defaultValue= "date_asc") DateOrderEnum order) {
        return productsService.getPostsFromFollowedSellers(userid, order);
    }

}
