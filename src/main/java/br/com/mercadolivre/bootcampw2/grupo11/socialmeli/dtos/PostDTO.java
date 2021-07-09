package br.com.mercadolivre.bootcampw2.grupo11.socialmeli.dtos;

import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.entities.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class PostDTO {
    private int idPost;
    private LocalDate date;
    private DetailsProductDTO detail;
    private int category;
    private double price;


    public PostDTO(Post post) {
        this.idPost = post.getId();
        this.date = post.getDate();
        this.detail = new DetailsProductDTO(post.getDetail());
        this.category = post.getCategory();
        this.price = post.getPrice().doubleValue();
    }
}
