package aston.greenteam.eventmanager.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventUpdateDTO {

    private Long id;
    private String title;
    private String description;
    private LocalDateTime startDatetime;
    private LocalDateTime endDatetime;
    private Boolean isActive;
    private String tags;
    private String linkEventSite;
    private String linkImage;
    private List<Long> idEventCategories;
    private List<Long> idEventPhotos;
    private List<Long> idUsers;

}
