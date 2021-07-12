package br.com.mercadolivre.bootcampw2.grupo11.socialmeli.dtos.post;

import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.entities.post.Product;
import lombok.Getter;

@Getter
public class DetailsProductDTO {
  private int productId;
  private String productName;
  private String type;
  private String brand;
  private String color;
  private String notes;

  public DetailsProductDTO(Product product) {
    this.productId = product.getId();
    this.productName = product.getProductName();
    this.type = product.getType();
    this.brand = product.getBrand();
    this.color = product.getColor();
    this.notes = product.getNotes();
  }
}
