package br.com.mercadolivre.bootcampw2.grupo11.socialmeli.repositories;

import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.entities.PromotionPost;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.entities.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PromotionalPostRepository extends JpaRepository<PromotionPost, Integer> {


    List<PromotionPost> findBySeller(Seller seller);
}
