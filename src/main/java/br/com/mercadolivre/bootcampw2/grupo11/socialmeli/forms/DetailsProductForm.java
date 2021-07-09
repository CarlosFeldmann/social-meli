package br.com.mercadolivre.bootcampw2.grupo11.socialmeli.forms;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class DetailsProductForm {

    @NotNull
    @NotEmpty
    @Schema(example = "Mechanical Keyboard Cherry MX BROWN", description = "Name of the product")
    private String productName;

    @NotNull
    @NotEmpty
    @Schema(example = "Keyboard", description = "The type of the product")
    private String type;

    @NotNull
    @NotEmpty
    @Schema(example = "Logitech", description = "The brand that made the product")
    private String brand;

    @NotNull
    @NotEmpty
    @Schema(example = "Black", description = "Color of the product")
    private String color;

    @NotNull
    @NotEmpty
    @Schema(example = "A very good keyboard with a attractive price", description = "Open field")
    private String notes;
}
