package aston.greenteam.eventmanager.services;

import aston.greenteam.eventmanager.dtos.EventPhotoPostDto;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.multipart.MultipartFile;

public interface EventPhotoService {
    FileSystemResource getPhoto(Long photoId);

    EventPhotoPostDto addEventPhoto(Long eventId, MultipartFile file);

    void deletePhoto(Long photoId);

}
