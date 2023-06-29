package aston.greenteam.eventmanager.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationUserDto {

    private String login;

    private String password;

    private String confirmPassword;

    private String nickname;

    private Integer age;

    private String gender;
}
