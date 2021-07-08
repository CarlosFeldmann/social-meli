package br.com.mercadolivre.bootcampw2.grupo11.socialmeli.services;

import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.dtos.UserDTO;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.dtos.UserFollowingListDTO;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.entities.Customer;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.entities.FollowDate;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.entities.Seller;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.exceptions.ApiException;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.exceptions.ResourceNotFoundException;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.forms.UserForm;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.repositories.CustomerRepository;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.repositories.SellerRepository;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class UsersService {

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private UserRepository userRepository;


    public UserDTO getUserInfo(Integer userId) {
        return userRepository.findById(userId)
                .map(UserDTO::fromEntity)
                .orElseThrow(() -> new ResourceNotFoundException("User", userId));
    }


    public UserDTO createCustomer(UserForm form) {
        var customer = new Customer();
        customer.setUserName(form.getUsername());
        customerRepository.save(customer);
        return UserDTO.fromEntity(customer);
    }

    public UserDTO createSeller(UserForm form) {
        var seller = new Seller();
        seller.setUserName(form.getUsername());
        sellerRepository.save(seller);
        return UserDTO.fromEntity(seller);
    }

    /**
     * This method is used for getting a list of seller that a given customer follows.
     * @param customerId - customer Id
     * @return The response to the end user.
     */
    public UserFollowingListDTO getFollowingList(Integer customerId) {
        var customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", customerId));
        var followList = customer.getFollowed().stream()
                .map(FollowDate::getSeller)
                .map(UserDTO::fromEntity)
                .collect(Collectors.toList());

        return new UserFollowingListDTO(customerId, customer.getUserName(), followList);
    }

    /**
     * This method is used to trigger following sequence for a customer following a seller
     * @param customerId - customer Id
     * @param sellerId - seller Id
     */
    public void follow(Integer customerId, Integer sellerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", customerId));
        Seller seller = sellerRepository.findById(sellerId)
                .orElseThrow(() -> new ResourceNotFoundException("Seller", sellerId));

        if (customer.isFollowing(seller))
            throw new ApiException(HttpStatus.BAD_REQUEST, "already_follows_error", "User already follows seller");

        customer.addFollow(seller);
        customerRepository.save(customer);
    }


}
