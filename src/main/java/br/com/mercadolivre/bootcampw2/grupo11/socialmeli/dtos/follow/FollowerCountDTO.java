package br.com.mercadolivre.bootcampw2.grupo11.socialmeli.dtos.follow;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FollowerCountDTO {
    private int userId;
    private String userName;
    private long followersCount;

}
