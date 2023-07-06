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

    public static List<EventPhoto> toEventPhotoList(Event event, List<MultipartFile> photoFile) {
        return photoFile
                .stream()
                .map(file -> PhotoMapper.toEventPhoto(event, file.getName()))
                .toList();
    }

    public static List<EventPhotoPostDto> toEventPhotoPostDto(List<EventPhoto> photos) {
        return photos
                .stream()
                .map(PhotoMapper::toPostDto)
                .toList();

    }
}
