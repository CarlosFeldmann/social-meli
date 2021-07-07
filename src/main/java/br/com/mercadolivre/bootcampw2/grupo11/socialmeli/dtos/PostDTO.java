package br.com.mercadolivre.bootcampw2.grupo11.socialmeli.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class PostDTO {
    private int idPost;
    private LocalDate date;
    private DetailsProductDTO detail;
    private int category;
    private double price;
}
