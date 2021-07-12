package br.com.mercadolivre.bootcampw2.grupo11.socialmeli.entities.follow;

import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.entities.user.Customer;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.entities.user.Seller;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

/** This is a pivot table for followers */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Follow {
  @EmbeddedId private FollowKey id;

  @ManyToOne
  @MapsId("userFollowerId")
  @JoinColumn(name = "user_follower_id")
  private Customer customer;

  @ManyToOne
  @MapsId("userFollowedId")
  @JoinColumn(name = "user_followed_id")
  private Seller seller;

  private LocalDate date;

  /**
   * @param customer - Customer who will perform the following action
   * @param seller - seller who will be followed
   */
  public Follow(Customer customer, Seller seller) {
    this.customer = customer;
    this.seller = seller;
    this.id = new FollowKey(customer.getUserId(), seller.getUserId());
    this.date = LocalDate.now();
  }
}
