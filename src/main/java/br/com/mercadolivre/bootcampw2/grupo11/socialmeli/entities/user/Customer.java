package br.com.mercadolivre.bootcampw2.grupo11.socialmeli.entities.user;

import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.entities.follow.Follow;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.exceptions.ResourceNotFoundException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;
/** This entity inherits from user and encapsulates the logic of users following sellers */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Customer extends User {
  @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<Follow> followed;

  /** @param seller - seller who will be followed */
  public void addFollow(Seller seller) {
    Follow follow = new Follow(this, seller);
    followed.add(follow);
  }

  /** @param seller - seller who will be unfollowed */
  public void removeFollow(Seller seller) {
    Follow followMatch =
        followed.stream()
            .filter(a -> a.getSeller().equals(seller))
            .findFirst()
            .orElseThrow(() -> new ResourceNotFoundException("Follow", seller.getUserId()));
    followed.remove(followMatch);
  }

  public boolean isFollowing(Seller seller) {
    return this.followed.stream().anyMatch(follow -> follow.getSeller().equals(seller));
  }
}
