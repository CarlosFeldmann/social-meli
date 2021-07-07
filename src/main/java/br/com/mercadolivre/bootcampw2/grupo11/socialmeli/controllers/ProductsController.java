package br.com.mercadolivre.bootcampw2.grupo11.socialmeli.controllers;

import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.forms.CreatePostForm;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.services.ProductsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductsService productsService;

    @Operation(description="Cria um novo post")
    @ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Post criado com sucesso"),
                            @ApiResponse(responseCode = "400", description = "Erro nos dados enviados"),
                            @ApiResponse(responseCode = "500", description = "Erro durante o processamento no servidor")})
    @PostMapping("/newpost")
    public ResponseEntity<HttpStatus> post(@RequestBody CreatePostForm Postform) {
        productsService.createNewPost(Postform);
        return (ResponseEntity<HttpStatus>) ResponseEntity.status(HttpStatus.CREATED);
    }

}
