package aston.greenteam.eventmanager.services.impl;

import aston.greenteam.eventmanager.dtos.EventDTO;
import aston.greenteam.eventmanager.entities.Event;
import aston.greenteam.eventmanager.entities.EventCategory;
import aston.greenteam.eventmanager.entities.User;
import aston.greenteam.eventmanager.exceptions.ObjectNotFoundException;
import aston.greenteam.eventmanager.exceptions.ValidationException;
import aston.greenteam.eventmanager.repositories.EventCategoryRepository;
import aston.greenteam.eventmanager.repositories.EventRepository;
import aston.greenteam.eventmanager.repositories.UserRepository;
import aston.greenteam.eventmanager.services.EventService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final EventCategoryRepository eventCategoryRepository;
    private final UserRepository userRepository;

    @Override
    public Event findById(Long id) {
        return eventRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Event with id:" + id + " not found."));
    }

    @Override
    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    @Override
    public List<Event> findAllByUserCreated(Long idUserCreated) {
        if (!(userRepository.existsById(idUserCreated))) {
            throw new ObjectNotFoundException("User with id: " + idUserCreated + " not found.");
        }
        User user = new User();
        user.setId(idUserCreated);
        return eventRepository.findAllByUser(user);
    }

    @Override
    public List<Event> findAllByTag(String tag) {
        if (tag.isBlank()) {
            throw new ValidationException("Tag field cannot be blank.");
        }
        return eventRepository.findAllByTagsIgnoreCase(tag);
    }

    @Override
    @Transactional
    public void createEvent(EventDTO eventDTO) {
        validation(eventDTO);
        Event event = eventFiller(eventDTO);
        eventRepository.save(event);
    }

    @Override
    public void deleteEventById(Long id) {
        if (!(eventRepository.existsById(id))) {
            throw new ObjectNotFoundException("Event with id: " + id + " not found.");
        }
        eventRepository.deleteById(id);
    }

    @Override // памагите я не знаю как сделать через optional
    public void updateEvent(EventDTO eventDTO) {
        Event eventToUpdate = eventExistValidation(eventDTO.getId());
        Optional<EventDTO> optionalEventDTO = Optional.of(eventDTO);
        if (eventDTO.getTitle() != null || !(eventDTO.getTitle().isBlank())) {
            eventToUpdate.setTitle(eventDTO.getTitle());
        }
        if (Optional.of(eventDTO.getDescription()).isPresent()) {
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

    private Event eventFiller(EventDTO eventDTO) {
        List<EventCategory> list = eventDTO.getIdCategories().stream()
                .map((id) -> eventCategoryRepository.findById(id)
                        .orElseThrow(() -> new ObjectNotFoundException("Категории с id: " + id + " не существует.")))
                .toList();
        User user = new User();
        user.setId(eventDTO.getIdUserCreated());
        Event event = new Event();
        event.setEventCategories(list);
        event.setTitle(eventDTO.getTitle());
        event.setDescription(eventDTO.getDescription());
        event.setLinkImage(eventDTO.getLinkImage());
        event.setPrice(eventDTO.getPrice());
        event.setTags(eventDTO.getTags());
        event.setStartDatetime(eventDTO.getStartDatetime());
        event.setEndDatetime(eventDTO.getEndDatetime());
        event.setUser(user);
        return event;
    }

    private void validation(EventDTO eventDTO) {
        if (eventDTO.getTitle().isBlank() || eventDTO.getTitle().isEmpty()) {
            throw new ValidationException("Поле названия не может быть пустым.");
        }
        if (eventDTO.getIdCategories().isEmpty()) {
            throw new ValidationException("Поле категорий ивента не может быть пустым.");
        }
        if (eventDTO.getTags().isBlank() || eventDTO.getTags().isEmpty()) {
            throw new ValidationException("Поле тега не может быть пустым.");
        }
    }

    private Event eventExistValidation(Long id) {
        return eventRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Event with id:" + id + " not found."));
    }

    public EventDTO mapEventToDTO(Event event) {
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