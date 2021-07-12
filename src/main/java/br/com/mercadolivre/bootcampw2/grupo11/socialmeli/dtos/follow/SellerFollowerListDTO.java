package br.com.mercadolivre.bootcampw2.grupo11.socialmeli.dtos.follow;

import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.dtos.UserDTO;
import lombok.Getter;

import java.util.List;

@Getter
public class SellerFollowerListDTO extends UserDTO {
  private List<UserDTO> followers;

  public SellerFollowerListDTO(int userId, String userName, List<UserDTO> followers) {
    super(userId, userName);
    this.followers = followers;
  }
}
