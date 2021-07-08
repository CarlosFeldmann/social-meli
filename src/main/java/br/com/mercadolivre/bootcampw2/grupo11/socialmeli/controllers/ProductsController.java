package br.com.mercadolivre.bootcampw2.grupo11.socialmeli.controllers;

import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.dtos.PostDTO;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.dtos.PostsBySellerDTO;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.entities.Post;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.forms.CreatePostForm;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.services.ProductsService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public PostsBySellerDTO postsFromSellersByUser(@PathVariable int userid) {
        return productsService.getPostsFromFollowedSellers(userid);
    }

}
