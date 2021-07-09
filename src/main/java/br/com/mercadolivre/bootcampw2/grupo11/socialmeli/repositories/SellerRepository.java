package br.com.mercadolivre.bootcampw2.grupo11.socialmeli.repositories;

import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.entities.user.Seller;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository extends BaseRepository<Seller, Integer> {


    @Query("select count(follow) from FollowDate follow where follow.seller = :seller")
    long countFollowers(@Param("seller") Seller seller);


    @Query("select count(post) from PromotionalPost post where post.seller = :seller")
    long countPromotionalPost(@Param("seller") Seller seller);
}
