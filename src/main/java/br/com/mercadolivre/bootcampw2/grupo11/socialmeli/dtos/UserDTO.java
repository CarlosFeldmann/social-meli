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


    public static UserDTO fromEntity(User user) {
        return new UserDTO(user.getUserId(), user.getUserName());
    }

}
