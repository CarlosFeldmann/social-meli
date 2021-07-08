package br.com.mercadolivre.bootcampw2.grupo11.socialmeli.forms;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
public class CreatePromocionalPostForm extends CreatePostForm {


    @NotNull
    private Boolean hasPromo;

    @NotNull
    @DecimalMin("0.01")
    @DecimalMax("1")
    private BigDecimal discount;
}
