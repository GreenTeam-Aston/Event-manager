package aston.greenteam.eventmanager.services.impl;

import aston.greenteam.eventmanager.dtos.EventCategoryDTO;
import aston.greenteam.eventmanager.dtos.EventCategoryDTOCreate;
import aston.greenteam.eventmanager.dtos.EventDTO;
import aston.greenteam.eventmanager.entities.Event;
import aston.greenteam.eventmanager.entities.EventCategory;
import aston.greenteam.eventmanager.exceptions.ObjectNotFoundException;
import aston.greenteam.eventmanager.mappers.EventCategoryMapper;
import aston.greenteam.eventmanager.mappers.EventMapper;
import aston.greenteam.eventmanager.repositories.EventCategoryRepository;
import aston.greenteam.eventmanager.services.EventCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventCategoryServiceImpl implements EventCategoryService {

    private final EventCategoryRepository eventCategoryRepository;
    private final EventMapper eventMapper;
    private final EventCategoryMapper eventCategoryMapper;

    @Override
    public List<EventCategoryDTO> findAll() {
        List<EventCategory> all = eventCategoryRepository.findAll();
        System.out.println(all);
        List<EventCategoryDTO> eventCategoryDTOS = new ArrayList<>();
        for (EventCategory eventCategory : all) {
            List<EventDTO> collect = eventCategory.getEvents().stream()
                    .map(eventMapper::mapEventToDTO)
                    .toList();
            EventCategoryDTO eventCategoryDTO = eventCategoryMapper.mapCategoryEventToDTO(eventCategory, collect);
            eventCategoryDTOS.add(eventCategoryDTO);
        }
        return eventCategoryDTOS;
    }

    @Override
    public EventCategoryDTO findById(Long id) {
        EventCategory eventCategory = eventCategoryRepository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException("Категория ивента с id:" + id + " не найден")
        );
        List<EventDTO> list = eventCategory.getEvents().stream()
                .map(eventMapper::mapEventToDTO).toList();
        return new EventCategoryDTO(eventCategory.getId(), eventCategory.getTitle(), list);
    }

    @Override
    public void addNewCategory(EventCategoryDTOCreate eventCategoryDTOCreate) {
        EventCategory eventCategory = new EventCategory();
        List<Event> events = new ArrayList<>();
        eventCategory.setTitle(eventCategoryDTOCreate.getTitle());
        eventCategory.setEvents(events);
        eventCategoryRepository.save(eventCategory);
    }

    @Override
    public void deleteCategory(Long id) {
        eventCategoryRepository.deleteById(id);
    }
}
