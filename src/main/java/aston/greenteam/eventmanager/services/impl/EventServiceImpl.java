package aston.greenteam.eventmanager.services.impl;

import aston.greenteam.eventmanager.dtos.EventDTO;
import aston.greenteam.eventmanager.entities.Event;
import aston.greenteam.eventmanager.entities.User;
import aston.greenteam.eventmanager.exceptions.ObjectNotFoundException;
import aston.greenteam.eventmanager.exceptions.ValidationException;
import aston.greenteam.eventmanager.repositories.EventRepository;
import aston.greenteam.eventmanager.services.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    public Event findById(Long id) {
        return eventRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Ивент с ид:" + id +" не найден"));
    }

    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    public List<Event> findAllByUserCreated(Long idUserCreated) {
        User user = new User();
        user.setId(idUserCreated);
        return eventRepository.findAllByUser(user);
    }

    @Override
    public List<Event> findAllByTag(String tag) {
        if (tag.isBlank()) {
            throw new ValidationException("Поле тега не может быть пустым");
        }
        return eventRepository.findAllByTagsIgnoreCase(tag);
    }

    public void createEvent(EventDTO eventDTO) {
        User user = new User();
        user.setId(eventDTO.getIdUserCreated());
        Event event = new Event();
        if (event.getTitle().isBlank() || event.getTitle().isEmpty()) {
            throw new ValidationException("Поле названия не может быть пустым.");
        }
        event.setTitle(eventDTO.getTitle());
        event.setDescription(eventDTO.getDescription());
        event.setLinkImage(eventDTO.getLinkImage());
        event.setPrice(eventDTO.getPrice());
        if (event.getTags().isBlank() || event.getTags().isEmpty()) {
            throw new ValidationException("Поле тега не может быть пустым.");
        }
        event.setTags(eventDTO.getTags());
        event.setUser(user);
        eventRepository.save(event);
    }

    public void deleteEventById(Long id) {
        if (!(eventRepository.existsById(id))) {
            throw new ObjectNotFoundException("Событие по ид: " + id + " не найдено.");
        }
        eventRepository.deleteById(id);
    }

    public EventDTO eventToDTO(Event event) {
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

    @Override
    public void updateEvent(EventDTO eventDTO) {
        Event eventToUpdate = eventRepository.findById(eventDTO.getId()).orElseThrow(() -> new ObjectNotFoundException("Ивент с ид:" + eventDTO.getId()  +" не найден"));
        if (eventDTO.getTitle() != null || !(eventDTO.getTitle().isBlank())) {
            eventToUpdate.setTitle(eventDTO.getTitle());
        }
        if (eventDTO.getDescription() != null) {
            eventToUpdate.setDescription(eventDTO.getDescription());
        }
        if (eventDTO.getLinkImage() != null) {
            eventToUpdate.setLinkImage(eventDTO.getLinkImage());
        }
        if (eventDTO.getPrice() != null) {
            eventToUpdate.setPrice(eventDTO.getPrice());
        }
        if (eventDTO.getTags() != null) {
            eventToUpdate.setTags(eventDTO.getTags());
        }
        eventRepository.save(eventToUpdate);
    }
}