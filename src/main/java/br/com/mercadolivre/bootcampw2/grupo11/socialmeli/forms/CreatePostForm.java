package br.com.mercadolivre.bootcampw2.grupo11.socialmeli.forms;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class CreatePostForm {

  @NotNull
  @Min(0)
  @Schema(example = "1", description = "Seller's ID that is creating this post")
  private Integer userId;

  @NotNull
  @DateTimeFormat(pattern = "dd-MM-yyyy")
  @Schema(
      type = "string",
      pattern = "\\d{1,2}-\\d{1,2}-\\d{4}",
      description = "Date that the post was created",
      example = "09-07-2021")
  private LocalDate date;

  @NotNull
  @Valid
  @Schema(description = "Details of the product in this post")
  private DetailsProductForm detail;

  @NotNull
  @Min(0)
  @Schema(example = "54", description = "Category ID of the product")
  private Integer category;

  @NotNull
  @DecimalMin(value = "0.01")
  @Schema(example = "153.50", description = "Price of the product in the post")
  private BigDecimal price;
}
