package aston.greenteam.eventmanager.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoticeDTO {

    private Long id;

    private String message;

    private NoticeCategoryDTO noticeCategory;

    private UserSimpleDTO userFrom;

    private UserSimpleDTO userTo;

    private EventDTO event;
}