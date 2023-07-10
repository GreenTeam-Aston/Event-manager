package aston.greenteam.eventmanager.controllers;

import aston.greenteam.eventmanager.dtos.EventPhotoPostDto;
import aston.greenteam.eventmanager.services.EventPhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@RestController
@RequestMapping("/photos")
@RequiredArgsConstructor
public class EventPhotoController {

    private final EventPhotoService service;

    @PostMapping(value = "/{eventId}")
    public EventPhotoPostDto addEventPhoto(@RequestParam("file") MultipartFile file,
                                           @PathVariable Long eventId) {
        return service.addEventPhoto(eventId, file);
    }

    @GetMapping(value = "/{photoId}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<Resource> getPhoto(@PathVariable Long photoId) {
        Resource resource = service.getPhoto(photoId);
        return ResponseEntity.of(Optional.of(resource));
    }

    @DeleteMapping("/{photoId}")
    public void getDelete(@PathVariable Long photoId) {
        service.deletePhoto(photoId);
    }

}




