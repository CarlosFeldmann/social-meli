package br.com.mercadolivre.bootcampw2.grupo11.socialmeli.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;
/**
 *  This entity inherits from user and encapsulates the logic of users following sellers
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Customer extends User{
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private Set<FollowDate> followed;

    /**
     *
     * @param seller - seller who will be followed
     */
    public void addFollow(Seller seller){
        FollowDateKey tmpFollowDateKey = new FollowDateKey(this.getUserId(),seller.getUserId());
        FollowDate tmpFollowDate = new FollowDate(this, seller);
        followed.add(tmpFollowDate);
    }
}
