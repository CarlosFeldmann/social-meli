package br.com.mercadolivre.bootcampw2.grupo11.socialmeli.dtos.promotional;

import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.dtos.promotional.PromotionalPostDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class ListPromotionalPostsBySellerDTO {
    private int userId;
    private String userName;
    private List<PromotionalPostDTO> posts;
}
