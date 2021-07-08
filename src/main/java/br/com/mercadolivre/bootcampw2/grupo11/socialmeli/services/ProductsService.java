package br.com.mercadolivre.bootcampw2.grupo11.socialmeli.services;

import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.dtos.*;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.entities.*;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.exceptions.ResourceNotFoundException;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.forms.CreatePostForm;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.forms.CreatePromocionalPostForm;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.forms.DateOrderEnum;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.repositories.*;
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

    @Autowired
    private PromotionalPostRepository promotionalPostRepository;


    public PostDTO createPost(CreatePostForm postForm) {
        Post newPost = createPostFromForm(postForm);
        postRepository.save(newPost);
        return new PostDTO(newPost);
    }

    public PromoPostDTO createPromotionalPost(CreatePromocionalPostForm form) {
        PromotionPost newPost = createPromotionalPostFromForm(form);
        postRepository.save(newPost);
        return new PromoPostDTO(newPost);
    }

    public PromoQuantityBySellerDTO getPromotionalPostsCountBySeller(Integer sellerId) {
        var seller = getSellerById(sellerId).orElseThrow(() -> new ResourceNotFoundException("Seller", sellerId));

        long promotionalPostCount = sellerRepository.countPromotionalPost(seller);
        return new PromoQuantityBySellerDTO(seller.getUserId(), seller.getUserName(), promotionalPostCount);
    }


    public ListPromoProductsBySellerDTO getPromotionalPostsBySeller(Integer sellerId) {
        var seller = getSellerById(sellerId).orElseThrow(() -> new ResourceNotFoundException("Seller", sellerId));
        var promotionalPostsDTO = this.promotionalPostRepository.findBySeller(seller).stream()
                .map(PromoPostDTO::new)
                .collect(Collectors.toList());

        return new ListPromoProductsBySellerDTO(seller.getUserId(), seller.getUserName(), promotionalPostsDTO);
    }


    public Optional<Seller> getSellerById(int id) {
        return sellerRepository.findById(id);
    }

    public PostsBySellerDTO getPostsFromFollowedSellers(int idUser, DateOrderEnum orderType) {
        PostsBySellerDTO postsBySellerDTO = new PostsBySellerDTO();

        var followedUsers = new ArrayList<FollowDate>(getSellersFollowedByUser(idUser));

        List<Post> posts = new ArrayList<Post>();

        LocalDate limitDate = LocalDate.now().minusWeeks(2);

        List<PostDTO> postsDTO = new ArrayList<PostDTO>();

        for (int i = 0; i < followedUsers.size(); i++) {
            ;
            posts.addAll(postRepository.getPostBySeller_userIdAndDateAfter(followedUsers.get(i).getSeller().getUserId(), limitDate));
        }
        for (int j = 0; j < posts.size(); j++) {
            postsDTO.add(createPostDTOFromPost(posts.get(j)));
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

    public Set<FollowDate> getSellersFollowedByUser(int idUser) {
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
        var seller = getSellerById(postForm.getUserId()).orElseThrow(() -> new ResourceNotFoundException("Seller", postForm.getUserId()));
        Post newPost = new Post();
        fillPostFromForm(newPost, postForm);
        newPost.setSeller(seller);
        return newPost;
    }

    private PromotionPost createPromotionalPostFromForm(CreatePromocionalPostForm form) {
        var seller = getSellerById(form.getUserId()).orElseThrow(() -> new ResourceNotFoundException("Seller", form.getUserId()));
        PromotionPost newPost = new PromotionPost();
        fillPostFromForm(newPost, form);
        newPost.setSeller(seller);
        newPost.setHasPromo(form.getHasPromo());
        newPost.setDiscount(form.getDiscount());
        return newPost;
    }


    private PostDTO createPostDTOFromPost(Post post) {
        if (post instanceof PromotionPost) {
            return new PromoPostDTO((PromotionPost) post);
        }
        return new PostDTO(post);
    }
}
