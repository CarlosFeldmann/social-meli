package br.com.mercadolivre.bootcampw2.grupo11.socialmeli.config.exceptions.dtos;

import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.exceptions.ApiException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ValidationErrorDTO extends ApiExceptionDTO {

    private List<FieldValidationErrorDTO> violations;

    public ValidationErrorDTO(HttpStatus statusCode, List<FieldValidationErrorDTO> fieldErrors) {
        super("field_constraint_violation", "One or more fields validation failed!", statusCode.value());
        this.violations = fieldErrors;
    }

}
