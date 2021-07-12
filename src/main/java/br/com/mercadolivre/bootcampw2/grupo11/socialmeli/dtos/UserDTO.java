package br.com.mercadolivre.bootcampw2.grupo11.socialmeli.dtos;

import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.entities.user.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTO {
  @Schema(example = "1")
  private int userId;

  @Schema(example = "Ednaldo")
  private String userName;

  public static UserDTO fromEntity(User user) {
    return new UserDTO(user.getUserId(), user.getUserName());
  }
}
