package br.com.mercadolivre.bootcampw2.grupo11.socialmeli.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ResourceNotFoundException extends ApiException {
    private String resource;
    private Integer resourceId;

    /**
     * @param resource - Type of the entity that was not found, example "User"
     * @param resourceId        - Key used to search for the entity, example : 1
     */
    public ResourceNotFoundException(String resource, Integer resourceId) {
        super(HttpStatus.NOT_FOUND, "resource_not_found", "Unable to find entity with the specified id!");
        this.resource = resource;
        this.resourceId = resourceId;
    }

}
