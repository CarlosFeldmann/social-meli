package br.com.mercadolivre.bootcampw2.grupo11.socialmeli.services;

import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.repositories.PostRepository;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductsService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ProductRepository productRepository;
}
