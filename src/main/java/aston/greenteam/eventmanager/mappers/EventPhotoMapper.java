package aston.greenteam.eventmanager.mappers;

import aston.greenteam.eventmanager.dtos.EventPhotoDTO;
import aston.greenteam.eventmanager.entities.EventPhoto;
import org.springframework.stereotype.Component;

@Component
public class EventPhotoMapper {

    public EventPhotoDTO mapEventPhotoToDTO(EventPhoto eventPhoto) {
        return EventPhotoDTO.builder()
                .id(eventPhoto.getId())
                .url(eventPhoto.getUrl())
                .build();
    };
}
