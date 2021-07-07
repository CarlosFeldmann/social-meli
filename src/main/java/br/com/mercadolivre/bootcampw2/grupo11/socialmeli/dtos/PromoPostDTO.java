package br.com.mercadolivre.bootcampw2.grupo11.socialmeli.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class PromoPostDTO extends PostDTO{
    private boolean hasPromo;
    private double discount;
}
