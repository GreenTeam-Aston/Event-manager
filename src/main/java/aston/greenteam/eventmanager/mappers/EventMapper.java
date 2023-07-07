package aston.greenteam.eventmanager.mappers;
import aston.greenteam.eventmanager.dtos.*;
import aston.greenteam.eventmanager.entities.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class EventMapper {
    private final EventCategoryMapper eventCategoryMapper;
    public EventDTO mapEventToDTO(Event event, UserSimpleDTO userSimpleDTO, List<EventCategorySimpleDTO> eventCategoryDTOList,
                                  List<EventPhotoDTO> eventPhotoDTOList, List<UserSimpleDTO> userSimpleDTOList) {
        List<EventCategorySimpleDTO> eventCategorySimpleDTOS = event.getEventCategories().stream()
                .map(eventCategoryMapper::mapCategoryEventToSimpleDTO).toList();
        return EventDTO.builder()
                .id(event.getId())
                .title(event.getTitle())
                .description(event.getDescription())
                .startDatetime(event.getStartDatetime())
                .endDatetime(event.getEndDatetime())
                .isActive(event.getIsActive())
                .userCreated(userSimpleDTO)
                .tags(event.getTags())
                .linkEventSite(event.getLinkEventSite())
                .linkImage(event.getLinkImage())
                .price(event.getPrice())
                .eventCategories(eventCategoryDTOList)
                .eventPhotos(eventPhotoDTOList)
                .users(userSimpleDTOList)
                .build();
    }
}
