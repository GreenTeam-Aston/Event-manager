package aston.greenteam.eventmanager.controllers;


import aston.greenteam.eventmanager.dtos.EventDTO;
import aston.greenteam.eventmanager.mappers.EventMapper;
import aston.greenteam.eventmanager.services.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;
    private final EventMapper eventMapper;

    @GetMapping
    public List<EventDTO> getAllEvents(){
        return eventService.findAll()
                .stream()
                .map(eventMapper::mapEventToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public EventDTO getById(@PathVariable Long id){
        return eventMapper.mapEventToDTO(eventService.findById(id));
    }

    @GetMapping("/user-created/{id}")
    public List<EventDTO> getByUserCreatedId(@PathVariable Long id) {
        return eventService.findAllByUserCreated(id)
                .stream()
                .map(eventMapper::mapEventToDTO)
                .collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<?> createEvent(@RequestBody EventDTO eventDTO) {
        eventService.createEvent(eventDTO);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeEventById(@PathVariable Long id) {
        eventService.deleteEventById(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/tag/{tag}")
    public List<EventDTO> findAllEventsByTags(@PathVariable String tag) {
        return eventService.findAllByTag(tag)
                .stream()
                .map(eventMapper::mapEventToDTO)
                .collect(Collectors.toList());
    }
    @PutMapping
    public ResponseEntity<?> updateEvent(@RequestBody EventDTO eventDTO) {
        eventService.updateEvent(eventDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}