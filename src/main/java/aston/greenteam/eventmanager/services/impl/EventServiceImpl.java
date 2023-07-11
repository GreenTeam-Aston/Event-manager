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
        return toDTO(event);
    }

    public List<EventDTO> findAll() {
        List<Event> eventRepositoryAll = eventRepository.findAll();
        return toListDTO(eventRepositoryAll);
    }

    public List<EventDTO> findAllByUserCreated(Long idUserCreated) {
        User user = userRepository.findById(idUserCreated).orElseThrow(() -> new ObjectNotFoundException("User with id:"
                + idUserCreated + " not exist."));
        List<Event> allByUser = eventRepository.findAllByUser(user);
        return toListDTO(allByUser);
    }

    @Override
    public List<EventDTO> findAllByTag(String tag) {
        if (tag.isBlank()) throw new ValidationException("The tage field cannot be empty.");
        List<Event> allByTagsIgnoreCase = eventRepository.findAllByTagsIgnoreCase(tag);
        return toListDTO(allByTagsIgnoreCase);
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
    public EventDTO updateEvent(EventUpdateDTO eventUpdateDTO, Long id) {
        Event event = eventRepository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException("Event with id:" + id + " not found.")
        );
        Optional.ofNullable(eventUpdateDTO.getTitle()).ifPresent(event::setTitle);
        Optional.ofNullable(eventUpdateDTO.getDescription()).ifPresent(event::setDescription);
        Optional.ofNullable(eventUpdateDTO.getStartDatetime()).ifPresent(event::setStartDatetime);
        Optional.ofNullable(eventUpdateDTO.getEndDatetime()).ifPresent(event::setEndDatetime);
        Optional.ofNullable(eventUpdateDTO.getIsActive()).ifPresent(event::setIsActive);
        Optional.ofNullable(eventUpdateDTO.getTags()).ifPresent(event::setTags);
        Optional.ofNullable(eventUpdateDTO.getLinkEventSite()).ifPresent(event::setLinkEventSite);
        Optional.ofNullable(eventUpdateDTO.getLinkImage()).ifPresent(event::setLinkImage);
        Optional.ofNullable(eventUpdateDTO.getLinkImage()).ifPresent(event::setLinkImage);
        if (eventUpdateDTO.getIdEventCategories() != null) {
            List<EventCategory> eventCategories = eventUpdateDTO.getIdEventCategories().stream()
                    .map(idEvent -> eventCategoryRepository.findById(idEvent).orElseThrow(
                            () -> new ObjectNotFoundException("Category with id:" + idEvent + " not exist.")
                    )).toList();
            event.getEventCategories().clear();
            event.getEventCategories().addAll(eventCategories);
        }
        if (eventUpdateDTO.getIdEventPhotos() != null) {
            List<EventPhoto> eventPhotos = eventUpdateDTO.getIdEventCategories().stream()
                    .map(idPhoto -> eventPhotoRepository.findById(idPhoto).orElseThrow(
                            () -> new ObjectNotFoundException("Photo with id:" + idPhoto + " not exist.")
                    )).toList();
            event.getEventPhotos().clear();
            event.getEventPhotos().addAll(eventPhotos);
        }
        if (eventUpdateDTO.getIdUsers() != null) {
            List<User> userList = eventUpdateDTO.getIdUsers().stream()
                    .map(idUser -> userRepository.findById(idUser).orElseThrow(
                            () -> new ObjectNotFoundException("User with id:" + idUser + " not exist.")
                    )).toList();
            event.getUsers().clear();
            event.getUsers().addAll(userList);
        }
        eventRepository.save(event);
        return toDTO(event);
    }

    private void validate(EventCreateDTO eventCreateDTO) {
        if (eventCreateDTO.getTitle().isBlank()) throw new ValidationException("The title field cannot be empty.");
        if (eventCreateDTO.getTags().isBlank()) throw new ValidationException("The tage field cannot be empty.");
        if (eventCreateDTO.getIdUserCreated() == null) throw new ValidationException("The id creator cannot be empty");
    }

    public List<EventDTO> toListDTO(List<Event> eventList) {
        List<EventDTO> eventDTOList = new ArrayList<>();
        for (Event event : eventList) {
            EventDTO eventDTO = toDTO(event);
            eventDTOList.add(eventDTO);
        }
        return eventDTOList;
    }

    public EventDTO toDTO(Event event) {
        List<EventCategorySimpleDTO> eventCategorySimpleDTOList = event.getEventCategories().stream()
                .map(eventCategoryMapper::mapCategoryEventToSimpleDTO)
                .toList();
        List<EventPhotoDTO> eventPhotoDTOList = event.getEventPhotos().stream()
                .map(eventPhotoMapper::mapEventPhotoToDTO)
                .toList();
        List<UserSimpleDTO> userSimpleDTOList = event.getUsers().stream()
                .map(userMapper::mapUserToSimpleDTO)
                .toList();
        UserSimpleDTO userSimpleDTO = userMapper.mapUserToSimpleDTO(event.getUser());
        return eventMapper.mapEventToDTO(event, userSimpleDTO, eventCategorySimpleDTOList,
                eventPhotoDTOList, userSimpleDTOList);
    }

    private Event fieldsEvent(EventCreateDTO eventCreateDTO) {
        Event event = new Event();
        event.setTitle(eventCreateDTO.getTitle());
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
        System.out.println(event);
        return event;
    }
}