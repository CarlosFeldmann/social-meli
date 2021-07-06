package br.com.mercadolivre.bootcampw2.grupo11.socialmeli.config.exceptions.dtos;


import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.exceptions.ApiException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiExceptionDTO {
    private String code;
    private String description;
    private Integer statusCode;

    public ApiExceptionDTO(ApiException exception) {
        this.code = exception.getCode();
        this.description = exception.getMessage();
        this.statusCode = exception.getStatusCode().value();
    }

    public ApiExceptionDTO(String code, String description, Integer statusCode) {
        this.code = code;
        this.description = description;
        this.statusCode = statusCode;
    }
}
