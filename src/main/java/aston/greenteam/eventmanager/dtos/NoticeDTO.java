package aston.greenteam.eventmanager.dtos;

import aston.greenteam.eventmanager.entities.User;
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
  private User userFrom;
  private User userTo;
  private EventDTO event;

}
