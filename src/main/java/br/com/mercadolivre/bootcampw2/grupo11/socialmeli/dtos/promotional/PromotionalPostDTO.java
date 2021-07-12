package br.com.mercadolivre.bootcampw2.grupo11.socialmeli.dtos.promotional;

import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.dtos.post.PostDTO;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.entities.post.PromotionalPost;
import lombok.Getter;

@Getter
public class PromotionalPostDTO extends PostDTO {
  private boolean hasPromo;
  private double discount;

  public PromotionalPostDTO(PromotionalPost post) {
    super(post);
    this.hasPromo = post.getHasPromo();
    this.discount = post.getDiscount().doubleValue();
  }
}
