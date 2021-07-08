package br.com.mercadolivre.bootcampw2.grupo11.socialmeli.controllers;

import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.dtos.ListPromoProductsBySellerDTO;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.dtos.PromoPostDTO;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.dtos.PromoQuantityBySellerDTO;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.forms.CreatePromocionalPostForm;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.services.ProductsService;
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

    private ProductsService productsService;

    @Autowired
    public PromotionalPostController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @Operation(description = "Create a promotional post")
    @PostMapping("/newpromopost")
    @ResponseStatus(HttpStatus.CREATED)
    public PromoPostDTO createPromotionalPost(@Valid @RequestBody CreatePromocionalPostForm form) {
        return productsService.createPromotionalPost(form);
    }


    @Operation(description = "Get promotional posts count for a given seller")
    @PostMapping("/{userId}/countPromo/")
    @ResponseStatus(HttpStatus.OK)
    public PromoQuantityBySellerDTO getPromotionalPostCountBySeller(@PathVariable(name = "userId") @Min(0) Integer sellerId) {
        return productsService.getPromotionalPostsCountBySeller(sellerId);
    }

    @Operation(description = "Get promotional posts from a given seller")
    @GetMapping("/{userId}/list/")
    @ResponseStatus(HttpStatus.OK)
    public ListPromoProductsBySellerDTO getPromotionalPostsBySeller(@PathVariable(name = "userId") @Min(0) Integer sellerId) {
        return productsService.getPromotionalPostsBySeller(sellerId);
    }

}
