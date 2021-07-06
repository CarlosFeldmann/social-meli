package br.com.mercadolivre.bootcampw2.grupo11.socialmeli.config.exceptions;


import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.config.exceptions.dtos.ApiExceptionDTO;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.config.exceptions.dtos.FieldValidationErrorDTO;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.config.exceptions.dtos.ResourceNotFoundExceptionDTO;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.config.exceptions.dtos.ValidationErrorDTO;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.exceptions.ApiException;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiExceptionDTO> handleApiException(ApiException exception) {
        var errorDto = new ApiExceptionDTO(exception);
        return ResponseEntity
                .status(exception.getStatusCode())
                .body(errorDto);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResourceNotFoundExceptionDTO> handleResourceNotFound(ResourceNotFoundException exception) {
        var errorDto = new ResourceNotFoundExceptionDTO(exception);
        return ResponseEntity
                .status(exception.getStatusCode())
                .body(errorDto);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorDTO> handleInvalidInput(MethodArgumentNotValidException exception) {
        var fieldErrors = exception.getFieldErrors().stream()
                .map(fieldError -> new FieldValidationErrorDTO(fieldError.getField(), fieldError.getDefaultMessage()))
                .collect(Collectors.toList());

        return ResponseEntity
                .badRequest()
                .body(new ValidationErrorDTO(HttpStatus.BAD_REQUEST, fieldErrors));

    }
}
