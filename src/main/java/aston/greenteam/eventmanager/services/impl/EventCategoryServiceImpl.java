package aston.greenteam.eventmanager.services.impl;

import aston.greenteam.eventmanager.dtos.EventCategoryDTO;
import aston.greenteam.eventmanager.dtos.EventCategoryCreateDTO;
import aston.greenteam.eventmanager.dtos.EventDTO;
import aston.greenteam.eventmanager.entities.Event;
import aston.greenteam.eventmanager.entities.EventCategory;
import aston.greenteam.eventmanager.exceptions.ObjectNotFoundException;
import aston.greenteam.eventmanager.mappers.EventCategoryMapper;
import aston.greenteam.eventmanager.repositories.EventCategoryRepository;
import aston.greenteam.eventmanager.services.EventCategoryService;
import aston.greenteam.eventmanager.services.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventCategoryServiceImpl implements EventCategoryService {

    private final EventCategoryRepository eventCategoryRepository;
    private final EventService eventService;
    private final EventCategoryMapper eventCategoryMapper;

    @Override
    public List<EventCategoryDTO> findAll() {
        List<EventCategory> all = eventCategoryRepository.findAll();
        System.out.println(all);
        List<EventCategoryDTO> eventCategoryDTOS = new ArrayList<>();
        for (EventCategory eventCategory : all) {
            List<EventDTO> collect = eventService.toListDTO(eventCategory.getEvents());
            EventCategoryDTO eventCategoryDTO = eventCategoryMapper.mapCategoryEventToDTO(eventCategory, collect);
            eventCategoryDTOS.add(eventCategoryDTO);
        }
        return eventCategoryDTOS;
    }

    @Override
    public EventCategoryDTO findById(Long id) {
        EventCategory eventCategory = eventCategoryRepository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException("Category event with id:" + id + " not found.")
        );
        List<EventDTO> list = eventService.toListDTO(eventCategory.getEvents());
        return new EventCategoryDTO(eventCategory.getId(), eventCategory.getTitle(), list);
    }

    @Override
    public void addNewCategory(EventCategoryCreateDTO eventCategoryCreateDTO) {
        EventCategory eventCategory = new EventCategory();
        List<Event> events = new ArrayList<>();
        eventCategory.setTitle(eventCategoryCreateDTO.getTitle());
        eventCategory.setEvents(events);
        eventCategoryRepository.save(eventCategory);
    }

    @Override
    public void deleteCategory(Long id) {
        eventCategoryRepository.deleteById(id);
    }
}