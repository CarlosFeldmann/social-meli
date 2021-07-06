package br.com.mercadolivre.bootcampw2.grupo11.socialmeli.config.exceptions.dtos;


import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.exceptions.ResourceNotFoundException;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundExceptionDTO extends ApiExceptionDTO {

    private String resource;
    private Long resourceId;

    public ResourceNotFoundExceptionDTO(ResourceNotFoundException exception) {
        super(exception);
        this.resource = exception.getResource();
        this.resourceId = exception.getResourceId();
    }

}
