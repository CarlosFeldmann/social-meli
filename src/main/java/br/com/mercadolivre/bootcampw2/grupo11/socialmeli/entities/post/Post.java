package br.com.mercadolivre.bootcampw2.grupo11.socialmeli.entities.post;

import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.entities.user.Seller;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

/** This entity associates posts to products in a OneToOne relationship */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "post")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Post {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "post_id")
  private Integer id;

  private LocalDate date;

  @Fetch(FetchMode.JOIN)
  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinColumn(name = "product_id", referencedColumnName = "product_id")
  private Product detail;

  private Integer category;

  private BigDecimal price;

  @ManyToOne private Seller seller;
}
