package aston.greenteam.eventmanager.services;

import aston.greenteam.eventmanager.dtos.EventCategoryDTO;
import aston.greenteam.eventmanager.dtos.EventCategoryDTOCreate;
import aston.greenteam.eventmanager.entities.EventCategory;
import java.util.List;

public interface EventCategoryService {
    List<EventCategoryDTO> findAll();
    EventCategoryDTO findById(Long id);
    void addNewCategory(EventCategoryDTOCreate eventCategoryDTOCreate);
    void deleteCategory(Long id);

}
