package br.com.mercadolivre.bootcampw2.grupo11.socialmeli.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

/**
 *  This entity inherits from user and encapsulates the logic of seller being followed by users
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Seller extends User{
    @OneToMany(mappedBy = "seller")
    private Set<FollowDate> followers;

    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Post> posts;
}
