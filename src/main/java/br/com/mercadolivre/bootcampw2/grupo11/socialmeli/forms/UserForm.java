package br.com.mercadolivre.bootcampw2.grupo11.socialmeli.forms;

import io.swagger.v3.oas.annotations.media.Schema;
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
  @Schema(example = "Ednaldo", description = "Username that will be registered")
  private String username;
}
