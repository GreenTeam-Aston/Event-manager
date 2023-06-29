package aston.greenteam.eventmanager.dtos;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTORegister {

    private Long id;
    private String login;
    private String password;
    private String nickname;
    private String userRoleString;

}
