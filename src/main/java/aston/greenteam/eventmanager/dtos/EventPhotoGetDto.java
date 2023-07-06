package aston.greenteam.eventmanager.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EventPhotoGetDto {
    private Long id;
    private Long eventId;
    private byte[] photo;
}
