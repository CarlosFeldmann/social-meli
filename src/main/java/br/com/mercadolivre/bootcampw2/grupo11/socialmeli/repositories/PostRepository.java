package br.com.mercadolivre.bootcampw2.grupo11.socialmeli.repositories;

import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.entities.user.Customer;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.entities.post.Post;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PostRepository extends BaseRepository<Post, Integer> {
    List<Post> getPostBySeller_userIdAndDateAfter(int userId, LocalDate twoWeeks);

    @Query("SELECT post FROM Post post " +
            "INNER JOIN Follow fd on post.seller = fd.seller " +
            "LEFT JOIN FETCH post.seller seller " +
            "LEFT JOIN FETCH post.detail detail " +
            "WHERE fd.customer = :customer " +
            "  and post.date >= :limitDate")
    List<Post> getPostsThatACustomerFollows(
            @Param("customer") Customer customer,
            @Param("limitDate") LocalDate limitDate,
            Sort sort
    );
}
