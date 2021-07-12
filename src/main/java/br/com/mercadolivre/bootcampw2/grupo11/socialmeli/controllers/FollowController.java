package br.com.mercadolivre.bootcampw2.grupo11.socialmeli.controllers;

import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.dtos.GenericMessageDTO;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.dtos.follow.FollowerCountDTO;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.dtos.follow.SellerFollowerListDTO;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.dtos.follow.UserFollowingListDTO;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.forms.ListOrderEnum;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.services.FollowService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;

@Tag(
    name = "Follow Controller",
    description = "Routes related to following actions and fetching follow related information")
@Validated
@RestController
@RequestMapping("/users")
public class FollowController {
  private final FollowService followService;

  @Autowired
  public FollowController(FollowService followService) {
    this.followService = followService;
  }

  @PostMapping("/{userId}/follow/{userIdToFollow}")
  @ResponseStatus(HttpStatus.OK)
  @Operation(
      summary = "follow seller",
      description = "[US0001] Add seller to customer following list")
  public GenericMessageDTO followSeller(
      @PathVariable @Min(0) Integer userId, @PathVariable @Min(0) Integer userIdToFollow) {
    followService.followSeller(userId, userIdToFollow);
    return new GenericMessageDTO("Seller followed successfully!");
  }

  @GetMapping("/{userId}/followers/count")
  @ResponseStatus(HttpStatus.OK)
  @Operation(
      summary = "seller follower count",
      description = "[US0002] Return the follower count of a seller.")
  public FollowerCountDTO followersCount(@PathVariable @Min(0) Integer userId) {
    return followService.getSellerFollowCount(userId);
  }

  @GetMapping("/{userId}/followers/list")
  @ResponseStatus(HttpStatus.OK)
  @Operation(
      summary = "customers following seller",
      description =
          "[US0003,US0008] List all customers that follow a given seller with a given sort method")
  public SellerFollowerListDTO listSellerFollowers(
      @PathVariable @Min(0) Integer userId,
      @RequestParam(defaultValue = "name_asc") ListOrderEnum order) {
    return followService.getFollowerList(userId, order);
  }

  @GetMapping("/{userId}/followed/list")
  @ResponseStatus(HttpStatus.OK)
  @Operation(
      summary = "get following sellers by customer",
      description = "[US0004,US0008] Get customer following list")
  public UserFollowingListDTO getSellersFollowedByUser(
      @PathVariable @Min(0) Integer userId,
      @RequestParam(defaultValue = "name_asc") ListOrderEnum order) {
    return followService.getFollowingList(userId, order);
  }

  @PostMapping("/{userId}/unfollow/{userIdToUnfollow}")
  @ResponseStatus(HttpStatus.OK)
  @Operation(
      summary = "unfollow seller",
      description =
          " [US0007] Unfollows customer to seller, userId refers to customer and userIdToUnfollow refers to seller")
  public GenericMessageDTO unfollow(
      @PathVariable @Min(0) Integer userId, @PathVariable @Min(0) Integer userIdToUnfollow) {
    followService.unfollowSeller(userId, userIdToUnfollow);
    return new GenericMessageDTO("Seller unfollowed successfully!");
  }
}
