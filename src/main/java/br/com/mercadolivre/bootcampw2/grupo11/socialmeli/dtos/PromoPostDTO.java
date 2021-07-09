package br.com.mercadolivre.bootcampw2.grupo11.socialmeli.dtos;

import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.entities.PromotionPost;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class PromoPostDTO extends PostDTO {
    private boolean hasPromo;
    private double discount;

    public PromoPostDTO(PromotionPost post) {
        super(post);
        this.hasPromo = post.getHasPromo();
        this.discount = post.getDiscount().doubleValue();
    }
}
