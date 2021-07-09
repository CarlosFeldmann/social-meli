package br.com.mercadolivre.bootcampw2.grupo11.socialmeli.dtos.promotional;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PromotionalQuantityBySellerDTO {
    private int userId;
    private String userName;
    @JsonProperty("promoproducts_count")
    private long promoProductsCount;
}
