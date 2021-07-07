package br.com.mercadolivre.bootcampw2.grupo11.socialmeli.forms;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class DetailsProductForm {

    @NotNull
    @NotEmpty
    private String productName;

    @NotNull
    @NotEmpty
    private String type;

    @NotNull
    @NotEmpty
    private String brand;

    @NotNull
    @NotEmpty
    private String color;

    @NotNull
    @NotEmpty
    private String notes;
}
