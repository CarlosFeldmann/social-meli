package br.com.mercadolivre.bootcampw2.grupo11.socialmeli.config.exceptions.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FieldValidationErrorDTO {
    private String field;
    private String message;
}
