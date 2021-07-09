package br.com.mercadolivre.bootcampw2.grupo11.socialmeli.forms;

import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.entities.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Sort;

@AllArgsConstructor
@Getter
public enum DateOrderEnum {
    date_asc(Sort.sort(Post.class).by(Post::getDate).ascending()),
    date_desc(Sort.sort(Post.class).by(Post::getDate).descending());

    private Sort sort;

}
