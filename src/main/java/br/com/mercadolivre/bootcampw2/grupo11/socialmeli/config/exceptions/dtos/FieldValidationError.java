package br.com.mercadolivre.bootcampw2.grupo11.socialmeli.config.exceptions.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FieldValidationError {

    @Schema(description = "The key of the field that the validation error occurred", example = "userName")
    private String field;
    @Schema(description = "Description of the error that occurred", example = "must have at least 5 characters")
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Schema(description = "The value that was passed to this field", example = "Joe")
    private Object invalidValue;

    public FieldValidationError(String field, String message) {
        this.field = field;
        this.message = message;
    }
}
