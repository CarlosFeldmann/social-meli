package br.com.mercadolivre.bootcampw2.grupo11.socialmeli.repositories;

import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.entities.user.Customer;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.entities.user.Seller;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SellerRepository extends BaseRepository<Seller, Integer> {


    @Query("select count(follow) from Follow follow where follow.seller = :seller")
    long countFollowers(@Param("seller") Seller seller);


    @Query("select count(post) from PromotionalPost post where post.seller = :seller")
    long countPromotionalPost(@Param("seller") Seller seller);

    @Query("SELECT sl FROM Seller sl " +
            "JOIN Follow f ON sl = f.seller " +
            "WHERE f.customer = :customer")
    List<Seller> getSellersFollowedBy(@Param("customer") Customer customer, Sort sort);

}
