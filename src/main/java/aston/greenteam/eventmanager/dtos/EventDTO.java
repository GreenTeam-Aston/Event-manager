package aston.greenteam.eventmanager.dtos;

import aston.greenteam.eventmanager.entities.EventPhoto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventDTO {

    private Long id;
    private String title;
    private String description;
    private LocalDateTime startDatetime;
    private LocalDateTime endDatetime;
    private Boolean isActive;
    private UserSimpleDTO userCreated;
    private String tags;
    private String linkEventSite;
    private String linkImage;
    private BigDecimal price;
    private List<EventCategorySimpleDTO> eventCategories;
    private List<EventPhotoDTO> eventPhotos;
    private List<UserSimpleDTO> users;
}
