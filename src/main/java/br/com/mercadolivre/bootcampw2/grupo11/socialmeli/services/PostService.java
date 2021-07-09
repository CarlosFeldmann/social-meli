package br.com.mercadolivre.bootcampw2.grupo11.socialmeli.services;

import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.dtos.PromotionalQuantityBySellerDTO;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.dtos.post.PostDTO;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.dtos.post.PostsBySellerDTO;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.dtos.promotional.ListPromotionalPostsBySellerDTO;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.dtos.promotional.PromotionalPostDTO;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.entities.follow.Follow;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.entities.post.Post;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.entities.post.Product;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.entities.post.PromotionalPost;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.exceptions.ResourceNotFoundException;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.forms.CreatePostForm;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.forms.CreatePromocionalPostForm;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.forms.DateOrderEnum;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PromotionalPostRepository promotionalPostRepository;


    public PostDTO createPost(CreatePostForm postForm) {
        Post newPost = createPostFromForm(postForm);
        postRepository.save(newPost);
        return new PostDTO(newPost);
    }

    public PromotionalPostDTO createPromotionalPost(CreatePromocionalPostForm form) {
        PromotionalPost newPost = createPromotionalPostFromForm(form);
        postRepository.save(newPost);
        return new PromotionalPostDTO(newPost);
    }

    public PromotionalQuantityBySellerDTO getPromotionalPostsCountBySeller(Integer sellerId) {
        var seller = sellerRepository.findByIdOrElseThrow(sellerId);

        long promotionalPostCount = sellerRepository.countPromotionalPost(seller);
        return new PromotionalQuantityBySellerDTO(seller.getUserId(), seller.getUserName(), promotionalPostCount);
    }


    public ListPromotionalPostsBySellerDTO getPromotionalPostsBySeller(Integer sellerId) {
        var seller = sellerRepository.findByIdOrElseThrow(sellerId);
        var promotionalPostsDTO = this.promotionalPostRepository.findBySeller(seller).stream()
                .map(PromotionalPostDTO::new)
                .collect(Collectors.toList());

        return new ListPromotionalPostsBySellerDTO(seller.getUserId(), seller.getUserName(), promotionalPostsDTO);
    }


    public PostsBySellerDTO getPostsFromFollowedSellers(int idUser, DateOrderEnum orderType) {

        var customer = customerRepository.findByIdOrElseThrow(idUser);

        LocalDate limitDate = LocalDate.now().minusWeeks(2);
        // Fetch posts from repository, returning sorted and filtering limit date
        List<Post> postsFromQuery = postRepository.getPostsThatACustomerFollows(customer, limitDate, orderType.getSort());

        // Converting to DTO
        var postsDTO = postsFromQuery.stream()
                .map(this::createPostDTOFromPost)
                .collect(Collectors.toList());
        return new PostsBySellerDTO(idUser, postsDTO);
    }

    public Set<Follow> getSellersFollowedByUser(int idUser) {
        var customer = customerRepository.findById(idUser).orElseThrow(()
                ->
                new ResourceNotFoundException("User Id", idUser));
        return customer.getFollowed();
    }


    private void fillPostFromForm(Post post, CreatePostForm form) {
        Product newProduct = new Product();
        post.setCategory(form.getCategory());
        post.setDate(form.getDate());
        newProduct.setBrand(form.getDetail().getBrand());
        newProduct.setColor(form.getDetail().getColor());
        newProduct.setNotes(form.getDetail().getNotes());
        newProduct.setProductName(form.getDetail().getProductName());
        newProduct.setType(form.getDetail().getType());
        post.setPrice(form.getPrice());
        post.setDetail(newProduct);
    }

    private Post createPostFromForm(CreatePostForm postForm) {
        var seller = sellerRepository.findByIdOrElseThrow(postForm.getUserId());

        Post newPost = new Post();
        fillPostFromForm(newPost, postForm);
        newPost.setSeller(seller);
        return newPost;
    }

    private PromotionalPost createPromotionalPostFromForm(CreatePromocionalPostForm form) {
        var seller = sellerRepository.findByIdOrElseThrow(form.getUserId());

        PromotionalPost newPost = new PromotionalPost();
        fillPostFromForm(newPost, form);
        newPost.setSeller(seller);
        newPost.setHasPromo(form.getHasPromo());
        newPost.setDiscount(form.getDiscount());
        return newPost;
    }


    private PostDTO createPostDTOFromPost(Post post) {
        if (post instanceof PromotionalPost) {
            return new PromotionalPostDTO((PromotionalPost) post);
        }
        return new PostDTO(post);
    }
}
