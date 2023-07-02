package aston.greenteam.eventmanager.services.impl;

import aston.greenteam.eventmanager.dtos.EventDTO;
import aston.greenteam.eventmanager.entities.Event;
import aston.greenteam.eventmanager.entities.User;
import aston.greenteam.eventmanager.repositories.EventRepository;
import aston.greenteam.eventmanager.services.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    public Event findById(Long id){
        return eventRepository.findById(id).orElseThrow();
    }

    public List<Event> findAll(){
        return eventRepository.findAll();
    }

    public List<Event> findAllByUserCreated(Long idUserCreated){
        User user = new User();
        user.setId(idUserCreated);
        return eventRepository.findAllByUser(user);
    }


    public void createEvent(EventDTO eventDTO){
        User user = new User();
        user.setId(eventDTO.getIdUserCreated());
        Event event = new Event();
        event.setTitle(eventDTO.getTitle());
        event.setDescription(eventDTO.getDescription());
        event.setLinkImage(eventDTO.getLinkImage());
        event.setPrice(eventDTO.getPrice());
        event.setTags(eventDTO.getTags());
        event.setUser(user);
        eventRepository.save(event);
    }

    public void deleteEventById(Long id){
        Event event = new Event();
        event.setId(id);
        eventRepository.delete(event);
    }


    public EventDTO eventToDTO(Event event){
        return EventDTO.builder()
                .id(event.getId())
                .description(event.getDescription())
                .startDatetime(event.getStartDatetime())
                .endDatetime(event.getEndDatetime())
                .linkImage(event.getLinkImage())
                .price(event.getPrice())
                .tags(event.getTags())
                .idUserCreated(event.getUser().getId())
                .build();
    }




}
