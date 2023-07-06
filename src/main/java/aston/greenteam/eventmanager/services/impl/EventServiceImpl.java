package aston.greenteam.eventmanager.services.impl;

import aston.greenteam.eventmanager.dtos.EventPhotoPostDto;
import aston.greenteam.eventmanager.entities.Event;
import aston.greenteam.eventmanager.entities.EventPhoto;
import aston.greenteam.eventmanager.exceptions.EventNotFoundException;
import aston.greenteam.eventmanager.exceptions.PhotoNotFoundException;
import aston.greenteam.eventmanager.mappers.PhotoMapper;
import aston.greenteam.eventmanager.repositories.EventPhotoRepository;
import aston.greenteam.eventmanager.repositories.EventRepository;
import aston.greenteam.eventmanager.services.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


// TODO доделать запись фото на диск в getAllEventPhotos, addAllEventPhotos, deletePhoto
@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventPhotoRepository photoRepository;
    private final EventRepository eventRepository;
    private final String PATH = "src/main/resources/photos/";

    @Override
    public FileSystemResource getPhoto(long photoId) {
        EventPhoto photo = photoRepository.findById(photoId)
                .orElseThrow(PhotoNotFoundException::new);
        return new FileSystemResource(photo.getPhotoUri());
    }

    @Override
    public EventPhotoPostDto addEventPhoto(long eventId, MultipartFile photoData) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(EventNotFoundException::new);
        File file = writeFile(photoData);
        EventPhoto newPhoto = PhotoMapper.toEventPhoto(event, file.getPath());
        newPhoto = photoRepository.save(newPhoto);
        return PhotoMapper.toPostDto(newPhoto);
    }

    @Override
    public void deletePhoto(long photoId) {
        photoRepository.deleteById(photoId);
    }

    @Override
    public List<FileSystemResource> getAllEventPhotos(Long eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(EventNotFoundException::new);
        return photoRepository.findAllByEvent(event)
                .stream()
                .map(photo -> new FileSystemResource(photo.getPhotoUri()))
                .toList();
    }

    @Override
    public List<EventPhotoPostDto> addAllEventPhotos(Long eventId, List<MultipartFile> photosFiles) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(EventNotFoundException::new);
        List<EventPhoto> photoList = PhotoMapper.toEventPhotoList(event, photosFiles);
        photoList = photoRepository.saveAll(photoList);
        return PhotoMapper.toEventPhotoPostDto(photoList);
    }

    private File writeFile(MultipartFile photoData) {
        File file;
        try {
            file = Files.createFile(Paths.get(PATH + photoData.getOriginalFilename())).toFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            fileOutputStream.write(photoData.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return file;
    }

}