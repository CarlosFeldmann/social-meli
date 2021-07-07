package br.com.mercadolivre.bootcampw2.grupo11.socialmeli.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class ListPromoProductsBySellerDTO {
    private int userId;
    private String userName;
    private List<PromoPostDTO> posts;
}
