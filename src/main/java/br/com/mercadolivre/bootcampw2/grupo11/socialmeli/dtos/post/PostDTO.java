package br.com.mercadolivre.bootcampw2.grupo11.socialmeli.dtos.post;

import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.entities.post.Post;
import lombok.Getter;

import java.time.LocalDate;

@Getter
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
