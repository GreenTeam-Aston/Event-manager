package aston.greenteam.eventmanager.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class UserDTO {

    private Long id;

    private String login;

    private String password;

    private String nickname;

    private Integer age;

    private String gender;

    private String userRoleString;
}
