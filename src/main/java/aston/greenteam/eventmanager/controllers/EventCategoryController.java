package aston.greenteam.eventmanager.controllers;

import aston.greenteam.eventmanager.dtos.EventCategoryDTO;
import aston.greenteam.eventmanager.dtos.EventCategoryDTOCreate;
import aston.greenteam.eventmanager.services.EventCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("api/v1/events-category")
@RequiredArgsConstructor
public class EventCategoryController {

    private final EventCategoryService eventCategoryService;
    @GetMapping
    public List<EventCategoryDTO> getAllCategories() {
        return eventCategoryService.findAll();
    }

    @GetMapping("/{eventCategoryId}")
    public EventCategoryDTO getCategory(@PathVariable Long eventCategoryId) {
        return eventCategoryService.findById(eventCategoryId);
    }

    @PostMapping
    public ResponseEntity<?> createEventCategory(@RequestBody EventCategoryDTOCreate eventCategoryDTOCreate) {
        eventCategoryService.addNewCategory(eventCategoryDTOCreate);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{eventCategoryId}")
    public ResponseEntity<?> deleteEventCategory(@PathVariable Long eventCategoryId) {
        eventCategoryService.deleteCategory(eventCategoryId);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
