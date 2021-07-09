package br.com.mercadolivre.bootcampw2.grupo11.socialmeli.dtos.post;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostsBySellerDTO {
    private int userId;
    private List<PostDTO> posts;
}
