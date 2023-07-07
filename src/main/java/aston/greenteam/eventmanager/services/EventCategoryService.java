package aston.greenteam.eventmanager.services;

import aston.greenteam.eventmanager.dtos.EventCategoryDTO;
import aston.greenteam.eventmanager.dtos.EventCategoryCreateDTO;

import java.util.List;

public interface EventCategoryService {
    List<EventCategoryDTO> findAll();
    EventCategoryDTO findById(Long id);
    void addNewCategory(EventCategoryCreateDTO eventCategoryCreateDTO);
    void deleteCategory(Long id);

}
