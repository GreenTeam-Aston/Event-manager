package aston.greenteam.eventmanager.mappers;
import aston.greenteam.eventmanager.dtos.EventCategorySimpleDTO;
import aston.greenteam.eventmanager.dtos.EventDTO;
import aston.greenteam.eventmanager.entities.Event;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class EventMapper {
    private final EventCategoryMapper eventCategoryMapper;
    public EventDTO mapEventToDTO(Event event) {
        List<EventCategorySimpleDTO> eventCategorySimpleDTOS = event.getEventCategories().stream()
                .map(eventCategoryMapper::mapCategoryEventToSimpleDTO).toList();
        return EventDTO.builder()
                .id(event.getId())
                .title(event.getTitle())
                .description(event.getDescription())
                .startDatetime(event.getStartDatetime())
                .endDatetime(event.getEndDatetime())
                .linkImage(event.getLinkImage())
                .price(event.getPrice())
                .tags(event.getTags())
                .idUserCreated(event.getUser().getId())
                .eventCategoryDTOS(eventCategorySimpleDTOS)
                .build();
    }
}
