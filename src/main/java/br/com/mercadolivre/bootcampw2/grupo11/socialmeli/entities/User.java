package br.com.mercadolivre.bootcampw2.grupo11.socialmeli.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue
    private long userId;
    private String userName;
}
