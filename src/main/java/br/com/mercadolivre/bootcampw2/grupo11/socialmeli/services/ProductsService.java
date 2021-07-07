package br.com.mercadolivre.bootcampw2.grupo11.socialmeli.services;

import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.entities.*;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.exceptions.ResourceNotFoundException;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.forms.CreatePostForm;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.repositories.CustomerRepository;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.repositories.PostRepository;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.repositories.ProductRepository;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.repositories.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductsService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public Post createNewPost(CreatePostForm postForm){
        Post newPost = createPostfromForm(postForm);
        return postRepository.save(newPost);
    }

    public Optional<Seller> getSellerById(int id){
        return sellerRepository.findById(id);
    }

    public List<Post> getPostsFromFollowedSellers(int idUser){
        //TODO: change returned object to DTO
        var followedUsers =new ArrayList<FollowDate>(getSellersFollowedByUser(idUser));

        List<Post> posts = new ArrayList<Post>();

        LocalDate limitDate = LocalDate.now().minusWeeks(2);

        for(int i=0; i< followedUsers.size(); i++){ ;
            posts.addAll(postRepository.getPostBySeller_userIdAndDateAfter(followedUsers.get(i).getSeller().getUserId(), limitDate));
        }

        return posts.stream()
                .sorted(Comparator.comparing(Post::getDate))
                .collect(Collectors.toList());
    }
    public Set<FollowDate> getSellersFollowedByUser(int idUser){
        return customerRepository.getById(idUser).getFollowed();
    }
    private Post createPostfromForm(CreatePostForm postForm){
        Post newPost = new Post();
        Product newProduct =  new Product();
        var seller = getSellerById(postForm.getUserId());

        if(seller.isPresent()) {
            newPost.setSeller(seller.get());
        }
        else{
            throw new ResourceNotFoundException("Seller Id", postForm.getUserId());
        }

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
