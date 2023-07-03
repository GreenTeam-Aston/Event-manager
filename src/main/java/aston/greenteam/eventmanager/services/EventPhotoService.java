package aston.greenteam.eventmanager.services;

import aston.greenteam.eventmanager.entities.EventPhoto;

public interface EventPhotoService {
    EventPhoto addEventPhoto(EventPhoto eventPhoto);
    String getEventPhoto(Long id);
}
