package aston.greenteam.eventmanager.mappers;

import aston.greenteam.eventmanager.dtos.EventCategoryDTO;
import aston.greenteam.eventmanager.dtos.EventCategorySimpleDTO;
import aston.greenteam.eventmanager.dtos.EventCreateDTO;
import aston.greenteam.eventmanager.dtos.EventDTO;
import aston.greenteam.eventmanager.entities.EventCategory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EventCategoryMapper {

    public EventCategoryDTO mapCategoryEventToDTO(EventCategory eventCategory, List<EventDTO> eventDTOList) {
        return EventCategoryDTO.builder()
                .id(eventCategory.getId())
                .title(eventCategory.getTitle())
                .events(eventDTOList)
                .build();
    }

    public EventCategorySimpleDTO mapCategoryEventToSimpleDTO(EventCategory eventCategory) {
        return EventCategorySimpleDTO.builder()
                .id(eventCategory.getId())
                .title(eventCategory.getTitle())
                .build();
    }
}
