package aston.greenteam.eventmanager.services;


import aston.greenteam.eventmanager.dtos.EventDTO;
import aston.greenteam.eventmanager.entities.Event;

import java.util.List;

public interface EventService {

    void createEvent(EventDTO eventDTO);
    void deleteEventById(Long id);
    Event findById(Long id);
    List<Event> findAll();
    List<Event> findAllByUserCreated(Long idUserCreated);

    List<Event> findAllByTag(String tag);

    public EventDTO eventToDTO(Event event);

    void updateEvent(EventDTO eventDTO);
}