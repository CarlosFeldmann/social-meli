package br.com.mercadolivre.bootcampw2.grupo11.socialmeli.forms;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UserForm {

    @NotNull
    @NotEmpty
    private String username;

}
