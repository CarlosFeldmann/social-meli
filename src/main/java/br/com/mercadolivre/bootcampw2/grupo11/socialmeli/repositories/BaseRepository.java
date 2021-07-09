package br.com.mercadolivre.bootcampw2.grupo11.socialmeli.repositories;

import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.exceptions.ResourceNotFoundException;
import org.springframework.core.GenericTypeResolver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;


/**
 * Base interface to all custom repositories, this is just a utility interface to declare common methods
 * @param <T> - The Entity that will be stored
 * @param <ID> - The entity key type
 */
@NoRepositoryBean
public interface BaseRepository<T, ID> extends JpaRepository<T, ID> {

    /**
     * Returns if found an entity T, otherwise throw a ResourceNotFoundException that will be handled at
     * a controller adviser
     * @throws ResourceNotFoundException - If doesn't find an entity with the given id
     * @param id - Id of the entity to find
     * @return T the entity itself
     */
    default T findByIdOrElseThrow(ID id) {
        var a = GenericTypeResolver.resolveTypeArguments(getClass(), BaseRepository.class);
        String resource = a != null && a.length > 0 ? a[0].getSimpleName() : "";

        return this.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(resource, id));
    }
}
