package aston.greenteam.eventmanager.controllers;


import aston.greenteam.eventmanager.dtos.EventCreateDTO;
import aston.greenteam.eventmanager.dtos.EventDTO;
import aston.greenteam.eventmanager.dtos.EventUpdateDTO;
import aston.greenteam.eventmanager.services.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;;

@RestController
@RequestMapping("api/v1/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @GetMapping
    public List<EventDTO> getAllEvents() {
        return eventService.findAll();
    }

    @GetMapping("/{id}")
    public EventDTO getById(@PathVariable Long id) {
        return eventService.findById(id);
    }

    @GetMapping("/user-created/{id}")
    public List<EventDTO> getByUserCreatedId(@PathVariable Long id) {
        return eventService.findAllByUserCreated(id);
    }

    @PostMapping()
    public ResponseEntity<?> createEvent(@RequestBody EventCreateDTO eventCreateDTO) {
        eventService.createEvent(eventCreateDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeEventById(@PathVariable Long id) {
        eventService.deleteEventById(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/tag/{tag}")
    public List<EventDTO> findAllEventsByTags(@PathVariable(required = true) String tag) {
        return eventService.findAllByTag(tag);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEvent(@PathVariable Long id, @RequestBody EventUpdateDTO eventUpdateDTO) {
        eventService.updateEvent(eventUpdateDTO, id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}