package aston.greenteam.eventmanager.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {

    private Long id;
    private String login;
    private String nickname;
    private Integer age;
    private String gender;
    private List<UserFriendDTO> userFriends;

}
