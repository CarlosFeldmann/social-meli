package br.com.mercadolivre.bootcampw2.grupo11.socialmeli.services;

import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.dtos.DetailsProductDTO;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.dtos.PostDTO;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.dtos.PostsBySellerDTO;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.entities.FollowDate;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.entities.Post;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.entities.Product;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.entities.Seller;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.exceptions.ResourceNotFoundException;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.forms.CreatePostForm;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.forms.DateOrderEnum;
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

    public PostsBySellerDTO getPostsFromFollowedSellers(int idUser, DateOrderEnum orderType){
        PostsBySellerDTO postsBySellerDTO = new PostsBySellerDTO();

        var followedUsers =new ArrayList<FollowDate>(getSellersFollowedByUser(idUser));

        List<Post> posts = new ArrayList<Post>();

        LocalDate limitDate = LocalDate.now().minusWeeks(2);

        List<PostDTO> postsDTO = new ArrayList<PostDTO>();

        for(int i=0; i< followedUsers.size(); i++){ ;
            posts.addAll(postRepository.getPostBySeller_userIdAndDateAfter(followedUsers.get(i).getSeller().getUserId(), limitDate));
        }
        for(int j=0; j< posts.size(); j++){
            postsDTO.add(createPostDTOfromPost(posts.get(j)));
        }

        var stream = postsDTO.stream();
        if (orderType == DateOrderEnum.date_desc) {
            stream = stream.sorted(Comparator.comparing(PostDTO::getDate).reversed());
        } else {
            stream = stream.sorted(Comparator.comparing(PostDTO::getDate));
        }

        postsBySellerDTO.setPosts(stream.collect(Collectors.toList()));
        postsBySellerDTO.setUserId(idUser);

        return postsBySellerDTO;
    }
    public Set<FollowDate> getSellersFollowedByUser(int idUser){
        var customer = customerRepository.findById(idUser).orElseThrow(()
                ->
                new ResourceNotFoundException("User Id", idUser ));
        return customer.getFollowed();
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
        newPost.setPrice(postForm.getPrice());
        newPost.setDetail(newProduct);
        return newPost;
    }
    private PostDTO createPostDTOfromPost(Post post){
        var postDTO = new PostDTO();
        var detailsDTO = new DetailsProductDTO();
        detailsDTO.setBrand(post.getDetail().getBrand());
        detailsDTO.setColor(post.getDetail().getColor());
        detailsDTO.setNotes(post.getDetail().getNotes());
        detailsDTO.setProductId(post.getDetail().getId());
        detailsDTO.setProductName(post.getDetail().getProductName());
        detailsDTO.setType(post.getDetail().getType());
        postDTO.setIdPost(post.getId());
        postDTO.setCategory(post.getCategory());
        postDTO.setDate(post.getDate());
        postDTO.setDetail(detailsDTO);
        postDTO.setPrice(post.getPrice().doubleValue());
        return postDTO;
    }
}
