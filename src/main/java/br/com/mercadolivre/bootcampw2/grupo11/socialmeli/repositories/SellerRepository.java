package br.com.mercadolivre.bootcampw2.grupo11.socialmeli.repositories;

import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.entities.Post;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.entities.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Integer> {
    Integer getFollowerCountBySellerId(Integer idSeller);

    void unfollowSeller(Integer idUser, Integer idSeller);

    List<Post> getPromoProductsBySellerId(Integer idSeller);
}
