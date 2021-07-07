package br.com.mercadolivre.bootcampw2.grupo11.socialmeli.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class FollowDateKey implements Serializable {

    @Column(name = "user_follower_id")
    private Integer userFollowerId;

    @Column (name = "user_followed_id")
    private Integer userFollowedId;

}
