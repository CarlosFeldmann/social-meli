package br.com.mercadolivre.bootcampw2.grupo11.socialmeli.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Seller extends User{
    @OneToMany(mappedBy = "seller")
    Set<FollowDate> followers;
    @OneToMany(mappedBy = "seller")
    List<Post> posts;
}
