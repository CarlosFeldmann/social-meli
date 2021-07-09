package br.com.mercadolivre.bootcampw2.grupo11.socialmeli.config.exceptions.dtos;


import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.exceptions.ResourceNotFoundException;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class ResourceNotFoundError extends ApiError {

    @Schema(description = "Resource type that wasn't present on the server", example = "User")
    private String resource;
    @Schema(type = "integer", description = "ID used to search for the resource", example = "1")
    private Object resourceId;

    public ResourceNotFoundError(ResourceNotFoundException exception) {
        super(exception);
        this.resource = exception.getResource();
        this.resourceId = exception.getResourceId();
    }

}
