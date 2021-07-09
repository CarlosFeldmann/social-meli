package br.com.mercadolivre.bootcampw2.grupo11.socialmeli.controllers;

import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.dtos.GenericMessageDTO;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.dtos.UserDTO;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.dtos.follow.FollowerCountDTO;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.dtos.follow.SellerFollowerListDTO;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.dtos.follow.UserFollowingListDTO;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.forms.ListOrderEnum;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.forms.UserForm;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.services.FollowService;
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

    private UserService userService;

    private FollowService followService;

    @Autowired
    public UserController(UserService userService, FollowService followService) {

        this.userService = userService;
        this.followService = followService;
    }

    @PostMapping("/customer")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(description = "Create a new customer user")
    public UserDTO createCustomer(@RequestBody @Valid UserForm form) {
        return userService.createCustomer(form);
    }

    @PostMapping("/seller")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(description = "Create a new seller user")
    public UserDTO createSeller(@RequestBody @Valid UserForm form) {
        return userService.createSeller(form);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "Fetch a user by id, works for customers and sellers")
    public UserDTO getUserInfo(@PathVariable @Min(0) Integer id) {
        return userService.getUserInfo(id);
    }

    @GetMapping("/{userId}/followed/list")
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "Get customer following list")
    public UserFollowingListDTO getSellersFollowedByUser(@PathVariable @Min(0) Integer userId) {
        return followService.getFollowingList(userId);

    }

    @GetMapping("/{userId}/followers/count")
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "Return the follower count of a seller.")
    public FollowerCountDTO followersCount(@PathVariable @Min(0) Integer userId) {
        return followService.getSellerFollowCount(userId);
    }

    @PostMapping("/{userId}/follow/{userIdToFollow}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "Add seller to customer following list")
    public GenericMessageDTO followSeller(@PathVariable @Min(0) Integer userId, @PathVariable @Min(0) Integer userIdToFollow) {
        followService.followSeller(userId, userIdToFollow);
        return new GenericMessageDTO("Seller followed successfully!");
    }

    @PostMapping("/{userId}/unfollow/{userIdToUnfollow}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "Unfollows customer to seller, userId refers to customer and userIdToUnfollow refers to seller")
    public GenericMessageDTO unfollow(@PathVariable @Min(0) Integer userId, @PathVariable @Min(0) Integer userIdToUnfollow) {
        followService.unfollowSeller(userId, userIdToUnfollow);
        return new GenericMessageDTO("Seller unfollowed successfully!");
    }


    @GetMapping("/{userId}/followers/list")
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "List all customers that follow a given seller and a given order")
    public SellerFollowerListDTO listSellerFollowers(@PathVariable @Min(0) Integer userId, @RequestParam(defaultValue = "name_asc") ListOrderEnum order) {
        return followService.getFollowerList(userId, order);
    }

}
