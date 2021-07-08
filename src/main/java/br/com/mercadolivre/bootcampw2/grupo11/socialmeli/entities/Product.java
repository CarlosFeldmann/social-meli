package br.com.mercadolivre.bootcampw2.grupo11.socialmeli.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Product {

    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "produt_name")
    private String productName;

    private String type;

    private String brand;

    private String color;

    private String notes;

    @OneToOne(mappedBy = "detail")
    private Post postId;

}
