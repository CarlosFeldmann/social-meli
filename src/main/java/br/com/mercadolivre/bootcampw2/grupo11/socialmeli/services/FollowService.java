package br.com.mercadolivre.bootcampw2.grupo11.socialmeli.services;

import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.dtos.follow.FollowerCountDTO;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.dtos.follow.SellerFollowerListDTO;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.dtos.UserDTO;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.dtos.follow.UserFollowingListDTO;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.entities.user.Customer;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.entities.follow.Follow;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.entities.user.Seller;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.exceptions.ApiException;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.forms.ListOrderEnum;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.repositories.CustomerRepository;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.repositories.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FollowService {

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private CustomerRepository customerRepository;

    /**
     * This method is used for getting a list of seller that a given customer follows.
     *
     * @param customerId - customer ID
     * @return The response to the end user.
     */
    public UserFollowingListDTO getFollowingList(Integer customerId) {
        var customer = customerRepository.findByIdOrElseThrow(customerId);
        var followList = customer.getFollowed().stream()
                .map(Follow::getSeller)
                .map(UserDTO::fromEntity)
                .collect(Collectors.toList());

        return new UserFollowingListDTO(customerId, customer.getUserName(), followList);
    }

    /**
     * This method is used to trigger following sequence for a customer following a seller
     *
     * @param customerId - customer ID
     * @param sellerId   - seller ID
     */
    public void followSeller(Integer customerId, Integer sellerId) {
        Customer customer = customerRepository.findByIdOrElseThrow(customerId);
        Seller seller = sellerRepository.findByIdOrElseThrow(sellerId);
        if (customer.isFollowing(seller))
            throw new ApiException(HttpStatus.BAD_REQUEST, "already_follows_error", "User already follows seller");

        customer.addFollow(seller);
        customerRepository.save(customer);
    }

    /**
     * This method is used to trigger unfollowing sequence for a customer unfollowing a seller
     *
     * @param customerId - customer ID
     * @param sellerId   - seller ID
     */
    public void unfollowSeller(Integer customerId, Integer sellerId) {
        Customer customer = customerRepository.findByIdOrElseThrow(customerId);
        Seller seller = sellerRepository.findByIdOrElseThrow(sellerId);

        if (!customer.isFollowing(seller))
            throw new ApiException(HttpStatus.BAD_REQUEST, "customer_isnt_following_error", "User already unfollows seller");

        customer.removeFollow(seller);

        customerRepository.save(customer);
    }

    /**
     * This method is used to list all followers from a given seller with an optional given order
     *
     * @param sellerId - seller ID
     * @param order    - order in which the follower list is ordered
     * @return Object DTO to the end user
     */
    public SellerFollowerListDTO getFollowerList(Integer sellerId, ListOrderEnum order) {
        Seller seller = sellerRepository.findByIdOrElseThrow(sellerId);

        List<Customer> followers = customerRepository.getCustomersFollowing(seller, order.getSort());

        var followListDTO = followers.stream()
                .map(UserDTO::fromEntity)
                .collect(Collectors.toList());

        return new SellerFollowerListDTO(sellerId, seller.getUserName(), followListDTO);
    }

    /**
     * Get the follower count of a given seller
     *
     * @param sellerId - ID of the seller
     * @return Human friendly response
     */
    public FollowerCountDTO getSellerFollowCount(Integer sellerId) {
        Seller seller = sellerRepository.findByIdOrElseThrow(sellerId);

        long followersCount = sellerRepository.countFollowers(seller);
        return new FollowerCountDTO(seller.getUserId(), seller.getUserName(), followersCount);
    }
}
