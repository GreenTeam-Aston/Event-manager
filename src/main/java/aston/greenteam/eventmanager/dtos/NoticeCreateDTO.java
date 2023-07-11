package aston.greenteam.eventmanager.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoticeCreateDTO {
    private String msg;
    private Long noticeCategoryId;
    private Long userFromId;
    private Long userToId;
    private Long eventId;
}
