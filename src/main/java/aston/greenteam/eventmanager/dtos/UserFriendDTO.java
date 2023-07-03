package aston.greenteam.eventmanager.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserFriendDTO {

    private Long id;
    private String login;
    private String nickname;
}
