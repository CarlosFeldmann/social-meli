package br.com.mercadolivre.bootcampw2.grupo11.socialmeli.services;

import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.dtos.UserInfoDTO;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.entities.user.Customer;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.entities.user.Seller;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.entities.user.User;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.forms.UserForm;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.repositories.CustomerRepository;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.repositories.SellerRepository;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final SellerRepository sellerRepository;

    private final CustomerRepository customerRepository;

    private final UserRepository userRepository;

    @Autowired
    public UserService(SellerRepository sellerRepository,
                       CustomerRepository customerRepository,
                       UserRepository userRepository) {
        this.sellerRepository = sellerRepository;
        this.customerRepository = customerRepository;
        this.userRepository = userRepository;
    }


    /**
     * Returns information about a given user
     *
     * @param userId - User
     * @return Human friendly user info response
     */
    public UserInfoDTO getUserInfo(Integer userId) {
        return buildUserInfoDTO(userRepository.findByIdOrElseThrow(userId));
    }


    /**
     * Create a customer user
     *
     * @param form User creation form
     * @return Human friendly user info response
     */
    public UserInfoDTO createCustomer(UserForm form) {
        var customer = new Customer();
        customer.setUserName(form.getUsername());
        customerRepository.save(customer);
        return buildUserInfoDTO(customer);
    }

    /**
     * Create a seller user
     *
     * @param form User creation form
     * @return Human friendly user info response
     */
    public UserInfoDTO createSeller(UserForm form) {
        var seller = new Seller();
        seller.setUserName(form.getUsername());
        sellerRepository.save(seller);
        return buildUserInfoDTO(seller);
    }

    /**
     * Build a user info from a given user
     *
     * @param user User
     * @return Human friendly response
     */
    private UserInfoDTO buildUserInfoDTO(User user) {
        return new UserInfoDTO(user.getUserId(), user.getUserName(), user.getClass().getSimpleName());
    }
}
