package br.com.mercadolivre.bootcampw2.grupo11.socialmeli.entities;

import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.exceptions.ResourceNotFoundException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Objects;
import java.util.Set;
/**
 *  This entity inherits from user and encapsulates the logic of users following sellers
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Customer extends User{
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<FollowDate> followed;

    /**
     *
     * @param seller - seller who will be followed
     */
    public void addFollow(Seller seller){
        FollowDate tmpFollowDate = new FollowDate(this, seller);
        followed.add(tmpFollowDate);
    }

    /**
     *
     * @param seller - seller who will be unfollowed
     */
    public void removeFollow(Seller seller){
        FollowDate followMatch = followed.stream()
                .filter(a -> a.getSeller().equals(seller))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Seller", seller.getUserId()));
        followed.remove(followMatch);
    }
}
