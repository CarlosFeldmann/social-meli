package br.com.mercadolivre.bootcampw2.grupo11.socialmeli.forms;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;


import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class CreatePostForm {

    @NotNull
    @NotEmpty
    @Min(0)
    private Integer userId;

    @NotNull
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate date;

    @NotNull
    private DetailsProductForm detail;

    @NotNull
    @NotEmpty
    @Min(0)
        private Integer category;

    @NotNull
    @DecimalMin(value = "0.01")
    private BigDecimal price;
}
