package br.com.mercadolivre.bootcampw2.grupo11.socialmeli.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DetailsProductDTO {
    private int productId;
    private String productName;
    private String type;
    private String brand;
    private String color;
    private String notes;
}
