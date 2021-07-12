package br.com.mercadolivre.bootcampw2.grupo11.socialmeli.controllers;

import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.dtos.UserInfoDTO;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.forms.UserForm;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;


@Tag(name = "User Controller", description = "Routes related to users, from creation to following")
@Validated
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/customer")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(description = "Create a new customer user", summary = "create a customer")
    public UserInfoDTO createCustomer(@RequestBody @Valid UserForm form) {
        return userService.createCustomer(form);
    }

    @PostMapping("/seller")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(description = "Create a new seller user", summary = "create a seller")
    public UserInfoDTO createSeller(@RequestBody @Valid UserForm form) {
        return userService.createSeller(form);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "Fetch a user by id, works for customers and sellers", summary = "fetch user")
    public UserInfoDTO getUserInfo(@PathVariable @Min(0) Integer id) {
        return userService.getUserInfo(id);
    }


}
