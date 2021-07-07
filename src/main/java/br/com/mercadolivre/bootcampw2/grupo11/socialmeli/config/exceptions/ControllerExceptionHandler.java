package br.com.mercadolivre.bootcampw2.grupo11.socialmeli.config.exceptions;


import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.config.exceptions.dtos.ApiError;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.config.exceptions.dtos.FieldValidationError;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.config.exceptions.dtos.ResourceNotFoundError;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.config.exceptions.dtos.ValidationError;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.exceptions.ApiException;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

@ControllerAdvice
public class ControllerExceptionHandler {

    /**
     * Handle all exceptions created by us in the API
     *
     * @param exception - Exception to be handled
     * @return Human friendly response
     */
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiError> handleApiException(ApiException exception) {
        var errorDto = new ApiError(exception);
        return ResponseEntity
                .status(errorDto.getStatusCode())
                .body(errorDto);
    }


    /**
     * Handle resource not found exception, that's not required because ResourceNotFound extends ApiError
     * But we want to give more information to the api user
     *
     * @param exception - Exception to be handled
     * @return Human friendly response
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResourceNotFoundError> handleResourceNotFound(ResourceNotFoundException exception) {
        var errorDto = new ResourceNotFoundError(exception);
        return ResponseEntity
                .status(exception.getStatusCode())
                .body(errorDto);
    }

    /**
     * This exception is thrown when a body validation fails
     *
     * @param exception - Exception to be handled
     * @return Human friendly response
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationError> handleInvalidInput(MethodArgumentNotValidException exception) {
        var fieldErrors = exception.getFieldErrors().stream()
                .map(fieldError -> new FieldValidationError(fieldError.getField(), fieldError.getDefaultMessage()))
                .collect(Collectors.toList());

        var dto = new ValidationError(HttpStatus.BAD_REQUEST, fieldErrors);

        return ResponseEntity
                .status(dto.getStatusCode())
                .body(dto);

    }

    /**
     * This exception is thrown when a request param validation fails
     *
     * @param exception - Exception to be handled
     * @return Human friendly response
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ValidationError> handleInvalidInput(ConstraintViolationException exception) {

        var fieldErrors = exception.getConstraintViolations().stream()
                .map(violation -> new FieldValidationError(violation.getPropertyPath().toString(), violation.getMessage(), violation.getInvalidValue()))
                .collect(Collectors.toList());

        var dto = new ValidationError(HttpStatus.BAD_REQUEST, fieldErrors);

        return ResponseEntity
                .status(dto.getStatusCode())
                .body(dto);

    }

    /**
     * This exception is thrown when the server can't parse the user input
     *
     * @param exception - Exception to be handled
     * @return Human friendly response
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiError> handleMissingParams(HttpMessageNotReadableException exception) {
        var dto = new ApiError("bad_request", "Unable to parse request body!", HttpStatus.BAD_REQUEST.value());
        return ResponseEntity
                .status(dto.getStatusCode())
                .body(dto);
    }

    /**
     * This exception is thrown when we don't have the route mapping that the user is looking for
     *
     * @param exception - Exception to be handled
     * @return Human friendly response
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ApiError> noRouteFound(HttpServletRequest req, NoHandlerFoundException exception) {
        ApiError apiError = new ApiError(
                "route_not_found",
                String.format("Route %s not found", req.getRequestURI()),
                HttpStatus.NOT_FOUND.value()
        );
        return ResponseEntity.status(apiError.getStatusCode())
                .body(apiError);
    }
}
