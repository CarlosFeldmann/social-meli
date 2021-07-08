package br.com.mercadolivre.bootcampw2.grupo11.socialmeli.services;

import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.entities.Post;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.entities.Product;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.entities.Seller;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.exceptions.ResourceNotFoundException;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.forms.CreatePostForm;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.repositories.PostRepository;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.repositories.ProductRepository;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.repositories.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductsService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SellerRepository sellerRepository;

    public Post createNewPost(CreatePostForm postForm){
        Post newPost = createPostfromForm(postForm);
        return postRepository.save(newPost);
    }

    public Optional<Seller> getSellerById(int id){
        return sellerRepository.findById(id);
    }

    private Post createPostfromForm(CreatePostForm postForm){
        Post newPost = new Post();
        Product newProduct =  new Product();
        var seller = getSellerById(postForm.getUserId()).orElseThrow(() -> new ResourceNotFoundException("Seller Id", postForm.getUserId()));
        newPost.setSeller(seller);
        newPost.setCategory(postForm.getCategory());
        newPost.setDate(postForm.getDate());
        newProduct.setBrand(postForm.getDetail().getBrand());
        newProduct.setColor(postForm.getDetail().getColor());
        newProduct.setNotes(postForm.getDetail().getNotes());
        newProduct.setProductName(postForm.getDetail().getProductName());
        newProduct.setType(postForm.getDetail().getType());
        newPost.setDetail(newProduct);
        return newPost;
    }
}
