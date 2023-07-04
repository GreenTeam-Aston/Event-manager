package aston.greenteam.eventmanager.dtos;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoticeCategoryDTO {

  private Long id;
  private String title;
  private List<NoticeDTO> allNotices;

}
