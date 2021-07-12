package br.com.mercadolivre.bootcampw2.grupo11.socialmeli.entities.post;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class PromotionalPost extends Post {

  private Boolean hasPromo;

  private BigDecimal discount;
}
