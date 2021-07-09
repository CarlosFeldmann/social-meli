package br.com.mercadolivre.bootcampw2.grupo11.socialmeli.dtos;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;

@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserInfoDTO extends UserDTO {

    private String userType;

    public UserInfoDTO(int userId, String userName, String userType) {
        super(userId, userName);
        this.userType = userType;
    }
}
