package br.com.mercadolivre.bootcampw2.grupo11.socialmeli.forms;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Getter
@Setter
@NoArgsConstructor
public class UserForm {

    @NotNull
    @NotEmpty
    private String username;

}
