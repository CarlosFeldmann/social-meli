package br.com.mercadolivre.bootcampw2.grupo11.socialmeli.dtos;


import lombok.Getter;

import java.util.List;

@Getter
public class UserFollowingListDTO extends UserDTO {
    private List<UserDTO> followed;

    public UserFollowingListDTO(int userId, String userName, List<UserDTO> followed) {
        super(userId, userName);
        this.followed = followed;
    }
}
