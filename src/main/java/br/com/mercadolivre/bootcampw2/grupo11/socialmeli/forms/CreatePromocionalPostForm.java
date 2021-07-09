package br.com.mercadolivre.bootcampw2.grupo11.socialmeli.forms;

import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(example = "true", description = "If this post have a promotional price")
    private Boolean hasPromo;

    @NotNull
    @DecimalMin("0.01")
    @DecimalMax("1")
    @Schema(example = "0.10", description = "Percentage of discount in the given price")
    private BigDecimal discount;
}
