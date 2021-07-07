package br.com.mercadolivre.bootcampw2.grupo11.socialmeli.config.exceptions.dtos;


import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.exceptions.ApiException;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ApiError {
    private String code;
    private String description;
    private Integer statusCode;
    private LocalDateTime timestamp;

    public ApiError(ApiException exception) {
        this.code = exception.getCode();
        this.description = exception.getMessage();
        this.statusCode = exception.getStatusCode().value();
        this.timestamp = LocalDateTime.now();
    }

    public ApiError(String code, String description, Integer statusCode) {
        this.code = code;
        this.description = description;
        this.statusCode = statusCode;
        this.timestamp = LocalDateTime.now();
    }
}
