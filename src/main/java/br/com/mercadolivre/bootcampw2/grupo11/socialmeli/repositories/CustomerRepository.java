package br.com.mercadolivre.bootcampw2.grupo11.socialmeli.repositories;

import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.entities.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends BaseRepository<Customer, Integer> {

    @Query("select count(o) from FollowDate o where o.customer = :customer")
    long countFollowing(@Param("customer") Customer customer);

}
