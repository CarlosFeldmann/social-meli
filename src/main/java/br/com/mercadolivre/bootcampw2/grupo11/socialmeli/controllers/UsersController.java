package br.com.mercadolivre.bootcampw2.grupo11.socialmeli.controllers;

import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.dtos.GenericMessageDTO;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.dtos.UserDTO;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.dtos.UserFollowingListDTO;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.forms.UserForm;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.services.UsersService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
@Validated
@RestController
@RequestMapping("/users")
public class UsersController {

    private UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping("/customer")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(description = "Create a new customer user")
    public UserDTO createCustomer(@RequestBody @Valid UserForm form) {
        return usersService.createCustomer(form);
    }

    @PostMapping("/seller")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(description = "Create a new seller user")
    public UserDTO createSeller(@RequestBody @Valid UserForm form) {
        return usersService.createSeller(form);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "Fetch a user by id, works for customers and sellers")
    public UserDTO getUserInfo(@PathVariable @Min(0) Integer id) {
        return usersService.getUserInfo(id);
    }

    @GetMapping("/{userId}/followed/list")
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "Get customer following list")
    public UserFollowingListDTO getSellersFollowedByUser(@PathVariable @Min(0) Integer userId){
        return usersService.getFollowingList(userId);
    }


    @PostMapping("/{userId}/follow/{userIdToFollow}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "Add seller to customer following list")
    public GenericMessageDTO followSeller(@PathVariable @Min(0) Integer userId, @PathVariable @Min(0) Integer userIdToFollow){
        usersService.follow(userId,userIdToFollow);
        return new GenericMessageDTO("Seller followed succesfully!");
    }
}
