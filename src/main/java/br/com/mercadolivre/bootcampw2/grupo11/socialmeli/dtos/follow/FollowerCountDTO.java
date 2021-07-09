package br.com.mercadolivre.bootcampw2.grupo11.socialmeli.dtos.follow;


import lombok.Getter;

@Getter
public class FollowerCountDTO extends UserDTO {
    private long followersCount;


    public FollowerCountDTO(int userId, String userName, long followersCount) {
        super(userId, userName);
        this.followersCount = followersCount;
    }
}
