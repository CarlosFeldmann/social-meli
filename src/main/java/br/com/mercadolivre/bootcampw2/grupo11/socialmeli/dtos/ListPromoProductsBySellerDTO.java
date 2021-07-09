package br.com.mercadolivre.bootcampw2.grupo11.socialmeli.dtos;

import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.dtos.promotional.PromotionalPostDTO;
import lombok.Getter;

import java.util.List;

@Getter
public class ListPromoProductsBySellerDTO extends UserDTO {
    private List<PromotionalPostDTO> posts;

    public ListPromoProductsBySellerDTO(int userId, String userName, List<PromotionalPostDTO> posts) {
        super(userId, userName);
        this.posts = posts;
    }
}
