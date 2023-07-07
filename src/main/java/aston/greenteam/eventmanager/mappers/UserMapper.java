package aston.greenteam.eventmanager.mappers;

import aston.greenteam.eventmanager.dtos.UserDTO;
import aston.greenteam.eventmanager.dtos.UserFriendDTO;
import aston.greenteam.eventmanager.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDTO mapUserToDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .login(user.getLogin())
                .nickname(user.getNickname())
                .age(user.getAge())
                .gender(user.getGender())
                .build();
    }

    public UserFriendDTO mapUserToUserFiendDTO(User user) {
        return UserFriendDTO.builder()
                .id(user.getId())
                .login(user.getLogin())
                .nickname(user.getNickname())
                .build();
    }
}
