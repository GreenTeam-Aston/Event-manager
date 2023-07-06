package aston.greenteam.eventmanager.services;

import aston.greenteam.eventmanager.dtos.EventPhotoPostDto;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EventPhotoService {
    FileSystemResource getPhoto(long photoId);

    EventPhotoPostDto addEventPhoto(long eventId, MultipartFile file);

    void deletePhoto(long photoId);

}
