package br.com.mercadolivre.bootcampw2.grupo11.socialmeli.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Product {
    @Id
    @Column(name = "product_id")
    private Long productId;
    @Column(name = "produt_name")
    private String productName;
    private String type;
    private String brand;
    private String colors;
    private String notes;
    @OneToOne(mappedBy = "detail")
    private Post postId;
}
