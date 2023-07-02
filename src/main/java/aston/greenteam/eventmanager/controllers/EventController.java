package aston.greenteam.eventmanager.controllers;


import aston.greenteam.eventmanager.dtos.EventDTO;
import aston.greenteam.eventmanager.services.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @GetMapping("/get-all")
    public List<EventDTO> getAllEvents(){
        return eventService.findAll()
                .stream()
                .map(eventService::eventToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/get-by-id/{id}")
    public EventDTO getById(@PathVariable Long id){
        return eventService.eventToDTO(eventService.findById(id));
    }

    @GetMapping("/get-all-events/{id}")
    public List<EventDTO> getByUserCreatedId(@PathVariable Long id) {
        return eventService.findAllByUserCreated(id)
                .stream()
                .map(eventService::eventToDTO)
                .collect(Collectors.toList());
    }

    //todo добавить обработку различных результатов
    @PostMapping("/create-event")
    public ResponseEntity<?> createEvent(@RequestBody EventDTO eventDTO) {
        eventService.createEvent(eventDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    //todo добавить обработку различных результатов
    @DeleteMapping("/remove-event/{id}")
    public ResponseEntity<?> removeEventById(@PathVariable Long id) {
        eventService.deleteEventById(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/get-all/{tag}") // TODO Протестить
    public List<EventDTO> findAllEventsByTags(@PathVariable(required = true) String tag) {
        return eventService.findAllByTag(tag)
                .stream()
                .map(eventService::eventToDTO)
                .collect(Collectors.toList());
    }
    @PutMapping("/update-event")
    public ResponseEntity<?> updateEvent(@RequestBody EventDTO eventDTO) {
        eventService.updateEvent(eventDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}