package br.com.mercadolivre.bootcampw2.grupo11.socialmeli.repositories;

import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.entities.post.Product;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends BaseRepository<Product, Integer> {}
