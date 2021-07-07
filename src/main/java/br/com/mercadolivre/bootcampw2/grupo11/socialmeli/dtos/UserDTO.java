package br.com.mercadolivre.bootcampw2.grupo11.socialmeli.dtos;

import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.entities.Customer;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.entities.Seller;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.entities.User;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.exceptions.ApiException;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class UserDTO {
    private int userId;
    private String userName;
    private UserType type;

    public static UserDTO fromEntity(User user) {
        UserType type;
        if (user instanceof Customer) {
            type = UserType.CUSTOMER;
        } else if (user instanceof Seller) {
            type = UserType.SELLER;
        } else {
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, "unable_to_convert_user", "Unable to convert user to dto!");
        }

        return new UserDTO(user.getUserId(), user.getUserName(), type);
    }


    public enum UserType {
        SELLER,
        CUSTOMER
    }

}
