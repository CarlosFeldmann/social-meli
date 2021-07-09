package br.com.mercadolivre.bootcampw2.grupo11.socialmeli.config.exceptions.dtos;


import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.exceptions.ApiException;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ApiError {
    @Schema(description = "Code of the exception that occurred",example = "internal_error")
    private String code;
    @Schema(description = "More specific details about the error",example = "A internal server error occurred!")
    private String description;
    @Schema(description = "HTTP Status code of the error",example = "500")
    private Integer statusCode;
    @Schema(description = "When the error was registered in the server")
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
