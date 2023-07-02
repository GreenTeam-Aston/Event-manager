package aston.greenteam.eventmanager.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
    private String linkImage;
    private BigDecimal price;
    private String tags;
    private Long idUserCreated;
}