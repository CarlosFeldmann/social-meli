package br.com.mercadolivre.bootcampw2.grupo11.socialmeli.repositories;

import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.entities.post.PromotionalPost;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.entities.user.Seller;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PromotionalPostRepository extends BaseRepository<PromotionalPost, Integer> {

  List<PromotionalPost> findBySeller(Seller seller);
}
