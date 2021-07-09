package br.com.mercadolivre.bootcampw2.grupo11.socialmeli.dtos;

import lombok.Getter;

import java.util.List;

@Getter
public class ListPromoProductsBySellerDTO extends UserDTO {
    private List<PromoPostDTO> posts;

    public ListPromoProductsBySellerDTO(int userId, String userName, List<PromoPostDTO> posts) {
        super(userId, userName);
        this.posts = posts;
    }
}
