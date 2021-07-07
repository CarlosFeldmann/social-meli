package br.com.mercadolivre.bootcampw2.grupo11.socialmeli.services;

import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.dtos.UserDTO;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.entities.Customer;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.entities.Seller;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.exceptions.ResourceNotFoundException;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.forms.UserForm;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.repositories.CustomerRepository;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.repositories.SellerRepository;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
                .orElseThrow(() -> new ResourceNotFoundException("User", userId.longValue()));
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


}
