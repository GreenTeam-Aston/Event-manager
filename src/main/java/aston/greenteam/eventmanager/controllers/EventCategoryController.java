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
@RequestMapping("/events-category")
@RequiredArgsConstructor
public class EventCategoryController {
    private final EventCategoryService eventCategoryService;
    @GetMapping("/get-all")
    public List<EventCategoryDTO> getAllCategories() {
        return eventCategoryService.findAll();
    }

    @GetMapping("/get-by-id/{eventCategoryId}")
    public EventCategoryDTO getCategory(@PathVariable Long eventCategoryId) {
        return eventCategoryService.findById(eventCategoryId);
    }

    @PostMapping("/create-events-category")
    public ResponseEntity<?> createEventCategory(@RequestBody EventCategoryDTOCreate eventCategoryDTOCreate) {
        eventCategoryService.addNewCategory(eventCategoryDTOCreate);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/delete-events-category/{eventCategoryId}")
    public ResponseEntity<?> deleteEventCategory(@PathVariable Long eventCategoryId) {
        eventCategoryService.deleteCategory(eventCategoryId);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
