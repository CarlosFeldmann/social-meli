package br.com.mercadolivre.bootcampw2.grupo11.socialmeli.dtos.promotional;

import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.dtos.UserDTO;
import lombok.Getter;

import java.util.List;

@Getter
public class ListPromotionalPostsBySellerDTO extends UserDTO {
  private List<PromotionalPostDTO> posts;

  public ListPromotionalPostsBySellerDTO(
      int userId, String userName, List<PromotionalPostDTO> posts) {
    super(userId, userName);
    this.posts = posts;
  }
}
