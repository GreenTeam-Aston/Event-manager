package aston.greenteam.eventmanager.controllers;

import aston.greenteam.eventmanager.dtos.EventPhotoPostDto;
import aston.greenteam.eventmanager.services.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/photos")
@RequiredArgsConstructor
public class EventController {

    private final EventService service;

    @PostMapping(value = "/{eventId}")
    public EventPhotoPostDto addEventPhoto(@RequestParam("file") MultipartFile file,
                                           @PathVariable Long eventId) {
        return service.addEventPhoto(eventId, file);
    }

    @PostMapping(value = "/all/{eventId}", produces = MediaType.IMAGE_JPEG_VALUE)
    public List<EventPhotoPostDto> addListOfEventPhotos(@RequestBody List<MultipartFile> file,
                                                        @PathVariable Long eventId) {
        return service.addAllEventPhotos(eventId, file);
    }

    @GetMapping(value = "/{photoId}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<Resource> getPhoto(@PathVariable Long photoId) {
        Resource resource = service.getPhoto(photoId);
        return toEntity(resource);
    }

    @GetMapping(value = "/all/{eventId}", produces = MediaType.IMAGE_JPEG_VALUE)
    public List<ResponseEntity<Resource>> getAllEventPhotos(@PathVariable Long eventId) {
        return service
                .getAllEventPhotos(eventId)
                .stream()
                .map(this::toEntity)
                .toList();
    }

    private ResponseEntity<Resource> toEntity(Resource resource) {
        ResponseEntity<Resource> responseEntity;
        try {
            responseEntity = ResponseEntity
                    .status(HttpStatus.OK)
                    .contentLength(resource.contentLength())
                    .body(resource);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return responseEntity;
    }

    @DeleteMapping("/{photoId}")
    public void getDelete(@PathVariable Long photoId) {
        service.deletePhoto(photoId);
    }

}




