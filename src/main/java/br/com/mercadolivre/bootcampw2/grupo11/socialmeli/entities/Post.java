package br.com.mercadolivre.bootcampw2.grupo11.socialmeli.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "post")
@EqualsAndHashCode
public class Post {
    @Id
    @GeneratedValue
    @Column(name = "post_id")
    private Integer id;

    private LocalDate date;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private Product detail;

    private Integer category;

    private BigDecimal price;

    @ManyToOne
    private Seller seller;
}
