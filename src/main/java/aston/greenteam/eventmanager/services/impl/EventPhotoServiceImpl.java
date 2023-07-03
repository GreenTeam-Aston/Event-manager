package aston.greenteam.eventmanager.services.impl;

import aston.greenteam.eventmanager.entities.EventPhoto;
import aston.greenteam.eventmanager.exceptions.ObjectNotFoundException;
import aston.greenteam.eventmanager.repositories.EventPhotoRepository;
import aston.greenteam.eventmanager.services.EventPhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventPhotoServiceImpl implements EventPhotoService {
    private EventPhotoRepository eventPhotoRepository;

    @Override
    public EventPhoto addEventPhoto(EventPhoto eventPhoto) {
        return eventPhotoRepository.save(eventPhoto);
    }

    @Override
    public String getEventPhoto(Long id) {
        EventPhoto eventPhoto = eventPhotoRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Не найдена фотка"));
        return eventPhoto.getUrl();
    }
}