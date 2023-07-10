package aston.greenteam.eventmanager.mappers;

import aston.greenteam.eventmanager.dtos.EventPhotoPostDto;
import aston.greenteam.eventmanager.entities.Event;
import aston.greenteam.eventmanager.entities.EventPhoto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class PhotoMapper {
    public static EventPhotoPostDto toPostDto(EventPhoto photo) {
        return  EventPhotoPostDto.builder()
                .photoUri(photo.getPhotoUri())
                .id(photo.getId())
                .build();
    }

    public static EventPhoto toEventPhoto(Event event, String photoUri) {
        return EventPhoto.builder()
                .event(event)
                .photoUri(photoUri)
                .build();
    }

}