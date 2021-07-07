package br.com.mercadolivre.bootcampw2.grupo11.socialmeli.entities;

import lombok.EqualsAndHashCode;
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
@EqualsAndHashCode
public class Product {

    @Id
    @Column(name = "product_id")
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
