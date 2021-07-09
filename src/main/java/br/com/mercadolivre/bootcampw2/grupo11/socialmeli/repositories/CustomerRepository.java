package br.com.mercadolivre.bootcampw2.grupo11.socialmeli.repositories;

import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.entities.user.Customer;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.entities.Seller;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends BaseRepository<Customer, Integer> {

    @Query("select count(o) from FollowDate o where o.customer = :customer")
    long countFollowing(@Param("customer") Customer customer);

    @Query("SELECT cs FROM Customer cs " +
            "JOIN FollowDate fd ON cs = fd.customer " +
            "WHERE fd.seller = :seller")
    List<Customer> getCustomersFollowing(
            @Param("seller") Seller seller,
            Sort sort
    );

}
