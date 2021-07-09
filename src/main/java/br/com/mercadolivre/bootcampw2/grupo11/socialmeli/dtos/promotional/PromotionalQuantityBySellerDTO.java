package br.com.mercadolivre.bootcampw2.grupo11.socialmeli.dtos;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class PromotionalQuantityBySellerDTO extends UserDTO {

    @JsonProperty("promoproducts_count")
    private long promoProductsCount;

    public PromotionalQuantityBySellerDTO(int userId, String userName, long promoProductsCount) {
        super(userId, userName);
        this.promoProductsCount = promoProductsCount;
    }
}
