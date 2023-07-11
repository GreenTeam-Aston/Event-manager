package aston.greenteam.eventmanager.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EventPhotoPostDto {
    private Long id;
    private String photoUri;
}
