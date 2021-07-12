package br.com.mercadolivre.bootcampw2.grupo11.socialmeli.services;

import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.dtos.PromotionalQuantityBySellerDTO;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.dtos.post.PostDTO;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.dtos.post.PostsBySellerDTO;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.dtos.promotional.ListPromotionalPostsBySellerDTO;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.dtos.promotional.PromotionalPostDTO;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.entities.post.Post;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.entities.post.Product;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.entities.post.PromotionalPost;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.forms.CreatePostForm;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.forms.CreatePromocionalPostForm;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.forms.DateOrderEnum;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.repositories.CustomerRepository;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.repositories.PostRepository;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.repositories.PromotionalPostRepository;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.repositories.SellerRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/** Service with the responsibility of creating, fetching and updating posts */
@Service
public class PostService {

  private final PostRepository postRepository;

  private final SellerRepository sellerRepository;

  private final CustomerRepository customerRepository;

  private final PromotionalPostRepository promotionalPostRepository;

  public PostService(
      PostRepository postRepository,
      SellerRepository sellerRepository,
      CustomerRepository customerRepository,
      PromotionalPostRepository promotionalPostRepository) {
    this.postRepository = postRepository;
    this.sellerRepository = sellerRepository;
    this.customerRepository = customerRepository;
    this.promotionalPostRepository = promotionalPostRepository;
  }

  /**
   * Create a new post
   *
   * @param form - user input
   * @return User friendly response
   */
  public PostDTO createPost(CreatePostForm form) {
    Post newPost = createPostFromForm(form);
    return new PostDTO(newPost);
  }

  /**
   * Create a new promotional post
   *
   * @param form - user input
   * @return User friendly response
   */
  public PromotionalPostDTO createPromotionalPost(CreatePromocionalPostForm form) {
    PromotionalPost newPost = createPromotionalPostFromForm(form);
    return new PromotionalPostDTO(newPost);
  }

  /**
   * Get the amount of promotional posts made by a specific seller
   *
   * @param sellerId - Seller id
   * @return User friendly response
   */
  public PromotionalQuantityBySellerDTO getPromotionalPostsCountBySeller(Integer sellerId) {
    var seller = sellerRepository.findByIdOrElseThrow(sellerId);

    long promotionalPostCount = sellerRepository.countPromotionalPost(seller);
    return new PromotionalQuantityBySellerDTO(
        seller.getUserId(), seller.getUserName(), promotionalPostCount);
  }

  /**
   * Get all promotional posts made by a specific seller
   *
   * @param sellerId - Seller id
   * @return User friendly response containing all posts
   */
  public ListPromotionalPostsBySellerDTO getPromotionalPostsBySeller(Integer sellerId) {
    var seller = sellerRepository.findByIdOrElseThrow(sellerId);
    var promotionalPostsDTO =
        this.promotionalPostRepository.findBySeller(seller).stream()
            .map(PromotionalPostDTO::new)
            .collect(Collectors.toList());

    return new ListPromotionalPostsBySellerDTO(
        seller.getUserId(), seller.getUserName(), promotionalPostsDTO);
  }

  /**
   * Get all posts from sellers that a given customer follows
   *
   * @param customerId - Id of the customer
   * @param orderType - Sorting logic that will be used
   * @return User friendly response containing all related posts
   */
  public PostsBySellerDTO getPostsFromFollowedSellers(Integer customerId, DateOrderEnum orderType) {

    var customer = customerRepository.findByIdOrElseThrow(customerId);

    LocalDate limitDate = LocalDate.now().minusWeeks(2);
    // Fetch posts from repository, returning sorted and filtering limit date
    List<Post> postsFromQuery =
        postRepository.getPostsThatACustomerFollows(customer, limitDate, orderType.getSort());

    // Converting to DTO
    var postsDTO =
        postsFromQuery.stream().map(this::createPostDTOFromPost).collect(Collectors.toList());
    return new PostsBySellerDTO(customerId, postsDTO);
  }

  /**
   * Fill a post entity with all values provided by the user This function exists so we can use the
   * same logic for a PromotionalPost
   *
   * @param post - Post that will be modified
   * @param form - User input
   */
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

  /**
   * Create a post entity based on user input
   *
   * @param form - User input
   * @return - Post Entity
   */
  private Post createPostFromForm(CreatePostForm form) {
    var seller = sellerRepository.findByIdOrElseThrow(form.getUserId());

    Post newPost = new Post();
    fillPostFromForm(newPost, form);
    newPost.setSeller(seller);
    postRepository.save(newPost);
    return newPost;
  }

  /**
   * Create a promotional post entity based on user input
   *
   * @param form - User input
   * @return - Post Entity
   */
  private PromotionalPost createPromotionalPostFromForm(CreatePromocionalPostForm form) {
    var seller = sellerRepository.findByIdOrElseThrow(form.getUserId());

    PromotionalPost newPost = new PromotionalPost();
    fillPostFromForm(newPost, form);
    newPost.setSeller(seller);
    newPost.setHasPromo(form.getHasPromo());
    newPost.setDiscount(form.getDiscount());
    postRepository.save(newPost);
    return newPost;
  }

  /**
   * Convert a post entity to a DTO This conversion will take in consideration the type of the post
   * PromotionalPost have a separated DTO
   *
   * <p>Use this method when you doesn't know which type of post you have(ex: Listing)
   *
   * @param post - Entity
   * @return User friendly response
   */
  private PostDTO createPostDTOFromPost(Post post) {
    if (post instanceof PromotionalPost) {
      return new PromotionalPostDTO((PromotionalPost) post);
    }
    return new PostDTO(post);
  }
}
