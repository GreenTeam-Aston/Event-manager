package aston.greenteam.eventmanager.services;

import aston.greenteam.eventmanager.dtos.EventPhotoPostDto;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EventPhotoService {
    FileSystemResource getPhoto(Long photoId);

    EventPhotoPostDto addEventPhoto(Long eventId, MultipartFile file);

    void deletePhoto(Long photoId);

}
