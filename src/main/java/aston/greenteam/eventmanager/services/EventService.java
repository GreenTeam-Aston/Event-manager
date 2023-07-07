package aston.greenteam.eventmanager.services;


import aston.greenteam.eventmanager.dtos.EventCreateDTO;
import aston.greenteam.eventmanager.dtos.EventDTO;
import aston.greenteam.eventmanager.dtos.EventUpdateDTO;
import aston.greenteam.eventmanager.entities.Event;

import java.util.List;

public interface EventService {

    void createEvent(EventCreateDTO eventCreateDTO);
    void deleteEventById(Long id);
    EventDTO findById(Long id);
    List<EventDTO> findAll();
    List<EventDTO> findAllByUserCreated(Long idUserCreated);

    List<EventDTO> findAllByTag(String tag);

    void updateEvent(EventUpdateDTO eventUpdateDTO);

    List<EventDTO> mapList(List<Event> eventList);
}