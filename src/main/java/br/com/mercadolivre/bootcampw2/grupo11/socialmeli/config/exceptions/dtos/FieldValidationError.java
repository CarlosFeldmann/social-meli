package br.com.mercadolivre.bootcampw2.grupo11.socialmeli.config.exceptions.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FieldValidationError {
    private String field;
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object invalidValue;

    public FieldValidationError(String field, String message) {
        this.field = field;
        this.message = message;
    }
}
