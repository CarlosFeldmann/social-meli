package br.com.mercadolivre.bootcampw2.grupo11.socialmeli.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Outro {

    @GeneratedValue
    @Id
    private Long id;

    private String name;

    public Outro(String name) {
        this.name = name;
    }

    @ManyToMany(mappedBy = "outros")
    private List<Test> testes;
}
