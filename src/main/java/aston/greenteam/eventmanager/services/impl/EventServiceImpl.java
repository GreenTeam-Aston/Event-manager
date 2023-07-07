package aston.greenteam.eventmanager.services.impl;

import aston.greenteam.eventmanager.dtos.*;
import aston.greenteam.eventmanager.entities.Event;
import aston.greenteam.eventmanager.entities.EventCategory;
import aston.greenteam.eventmanager.entities.EventPhoto;
import aston.greenteam.eventmanager.entities.User;
import aston.greenteam.eventmanager.exceptions.ObjectNotFoundException;
import aston.greenteam.eventmanager.exceptions.ValidationException;
import aston.greenteam.eventmanager.mappers.EventCategoryMapper;
import aston.greenteam.eventmanager.mappers.EventMapper;
import aston.greenteam.eventmanager.mappers.EventPhotoMapper;
import aston.greenteam.eventmanager.mappers.UserMapper;
import aston.greenteam.eventmanager.repositories.*;
import aston.greenteam.eventmanager.services.EventService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final EventPhotoRepository eventPhotoRepository;
    private final EventCategoryRepository eventCategoryRepository;
    private final EventMapper eventMapper;
    private final EventCategoryMapper eventCategoryMapper;
    private final EventPhotoMapper eventPhotoMapper;
    private final UserMapper userMapper;

    public EventDTO findById(Long id) {
        Event event = eventRepository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException("Event with id:" + id + " not exist.")
        );
        return map(event);
    }

    public List<EventDTO> findAll() {
        List<Event> eventRepositoryAll = eventRepository.findAll();
        return mapList(eventRepositoryAll);
    }

    public List<EventDTO> findAllByUserCreated(Long idUserCreated) {
        User user = userRepository.findById(idUserCreated).orElseThrow(() -> new ObjectNotFoundException("User with id:"
                + idUserCreated + " not exist."));
        List<Event> allByUser = eventRepository.findAllByUser(user);
        return mapList(allByUser);
    }

    @Override
    public List<EventDTO> findAllByTag(String tag) {
        if (tag.isBlank()) throw new ValidationException("The tage field cannot be empty.");
        List<Event> allByTagsIgnoreCase = eventRepository.findAllByTagsIgnoreCase(tag);
        return mapList(allByTagsIgnoreCase);
    }

    @Transactional
    public void createEvent(EventCreateDTO eventCreateDTO) {
        validate(eventCreateDTO);
        Event event = fieldsEvent(eventCreateDTO);
        eventRepository.save(event);
    }

    public void deleteEventById(Long id) {
        if (!(eventRepository.existsById(id))) {
            throw new ObjectNotFoundException("Event with id:" + id + " not exist.");
        }
        eventRepository.deleteById(id);
    }

    @Override
    public void updateEvent(EventUpdateDTO eventUpdateDTO) {
        if (eventUpdateDTO.getId() == null) throw new ValidationException("The id field cannot be null.");
        Event event = eventRepository.findById(eventUpdateDTO.getId()).orElseThrow(
                () -> new ObjectNotFoundException("Event with id:" + eventUpdateDTO.getId() + " not found.")
        );
        Optional.ofNullable(event.getTitle()).ifPresent(event::setTitle);
        Optional.ofNullable(eventUpdateDTO.getDescription()).ifPresent(event::setDescription);
        Optional.ofNullable(eventUpdateDTO.getStartDatetime()).ifPresent(event::setStartDatetime);
        Optional.ofNullable(eventUpdateDTO.getEndDatetime()).ifPresent(event::setEndDatetime);
        Optional.ofNullable(eventUpdateDTO.getIsActive()).ifPresent(event::setIsActive);
        Optional.ofNullable(eventUpdateDTO.getTags()).ifPresent(event::setTags);
        Optional.ofNullable(eventUpdateDTO.getLinkEventSite()).ifPresent(event::setLinkEventSite);
        Optional.ofNullable(eventUpdateDTO.getLinkImage()).ifPresent(event::setLinkImage);
        Optional.ofNullable(eventUpdateDTO.getLinkImage()).ifPresent(event::setLinkImage);
        if (!eventUpdateDTO.getIdEventCategories().isEmpty()) {
            List<EventCategory> eventCategories = eventUpdateDTO.getIdEventCategories().stream()
                    .map(id -> eventCategoryRepository.findById(id).orElseThrow(
                            () -> new ObjectNotFoundException("Category with id:" + id + " not exist.")
                    )).toList();
            event.setEventCategories(eventCategories);
        }
        if (!eventUpdateDTO.getIdEventPhotos().isEmpty()) {
            List<EventPhoto> eventPhotos = eventUpdateDTO.getIdEventCategories().stream()
                    .map(id -> eventPhotoRepository.findById(id).orElseThrow(
                            () -> new ObjectNotFoundException("Photo with id:" + id + " not exist.")
                    )).toList();
            event.setEventPhotos(eventPhotos);
        }
        if (!eventUpdateDTO.getIdUsers().isEmpty()) {
            List<User> userList = eventUpdateDTO.getIdUsers().stream()
                    .map(id -> userRepository.findById(id).orElseThrow(
                            () -> new ObjectNotFoundException("User with id:" + id + " not exist.")
                    )).toList();
            event.setUsers(userList);
        }
        eventRepository.save(event);
    }

    private void validate(EventCreateDTO eventCreateDTO) {
        if (eventCreateDTO.getTitle().isBlank()) throw new ValidationException("The title field cannot be empty.");
        if (eventCreateDTO.getTags().isBlank()) throw new ValidationException("The tage field cannot be empty.");
        if (eventCreateDTO.getIdEventCategories().isEmpty())
            throw new ValidationException("Categories must be specified.");
        if (eventCreateDTO.getIsActive() == null) throw new ValidationException("The active field cannot be empty.");
        if (eventCreateDTO.getIdUserCreated() == null) throw new ValidationException("The id creator cannot be empty");
    }

    public List<EventDTO> mapList(List<Event> eventList) {
        List<EventDTO> eventDTOList = new ArrayList<>();
        for (Event event : eventList) {
            EventDTO eventDTO = map(event);
            eventDTOList.add(eventDTO);
        }
        return eventDTOList;
    }

    private EventDTO map(Event event) {
        List<EventCategorySimpleDTO> eventCategorySimpleDTOList = event.getEventCategories().stream()
                .map(eventCategoryMapper::mapCategoryEventToSimpleDTO)
                .toList();
        List<EventPhotoDTO> eventPhotoDTOList = event.getEventPhotos().stream()
                .map(eventPhotoMapper::mapEventPhotoToDTO)
                .toList();
        List<UserDTO> userDTOList = event.getUsers().stream()
                .map(userMapper::mapUserToDTO)
                .toList();
        UserDTO userDTO = userMapper.mapUserToDTO(event.getUser());
        return eventMapper.mapEventToDTO(event, userDTO, eventCategorySimpleDTOList,
                eventPhotoDTOList, userDTOList);
    }

    private Event fieldsEvent(EventCreateDTO eventCreateDTO) {
        Event event = new Event();
        event.setTitle(event.getTitle());
        Optional.ofNullable(eventCreateDTO.getDescription()).ifPresent(event::setDescription);
        Optional.ofNullable(eventCreateDTO.getStartDatetime()).ifPresent(event::setStartDatetime);
        Optional.ofNullable(eventCreateDTO.getEndDatetime()).ifPresent(event::setEndDatetime);
        event.setIsActive(eventCreateDTO.getIsActive());
        User user = userRepository.findById(eventCreateDTO.getIdUserCreated()).orElseThrow(
                () -> new ObjectNotFoundException("User with id:" + eventCreateDTO.getIdUserCreated() + " not exist.")
        );
        event.setUser(user);
        event.setTags(eventCreateDTO.getTags());
        List<EventCategory> list = eventCreateDTO.getIdEventCategories().stream()
                .map(id -> eventCategoryRepository.findById(id).orElseThrow(
                        () -> new ObjectNotFoundException("Category with id:" + id + " not exist.")
                )).toList();
        event.setEventCategories(list);
        return event;
    }
}