package aston.greenteam.eventmanager.controllers;


import aston.greenteam.eventmanager.dtos.EventCreateDTO;
import aston.greenteam.eventmanager.dtos.EventDTO;
import aston.greenteam.eventmanager.dtos.EventUpdateDTO;
import aston.greenteam.eventmanager.mappers.EventMapper;
import aston.greenteam.eventmanager.services.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;
    private final EventMapper eventMapper;

    @GetMapping("/get-all")
    public List<EventDTO> getAllEvents() {
        return eventService.findAll();
    }

    @GetMapping("/get-by-id/{id}")
    public EventDTO getById(@PathVariable Long id) {
        return eventService.findById(id);
    }

    @GetMapping("/get-all-events/{id}")
    public List<EventDTO> getByUserCreatedId(@PathVariable Long id) {
        return eventService.findAllByUserCreated(id);
    }

    @PostMapping("/create-event")
    public void createEvent(@RequestBody EventCreateDTO eventCreateDTO) {
        eventService.createEvent(eventCreateDTO);
    }

    @DeleteMapping("/remove-event/{id}")
    public ResponseEntity<?> removeEventById(@PathVariable Long id) {
        eventService.deleteEventById(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/get-all/{tag}")
    public List<EventDTO> findAllEventsByTags(@PathVariable(required = true) String tag) {
        return eventService.findAllByTag(tag);
    }

    @PutMapping("/update-event")
    public ResponseEntity<?> updateEvent(@RequestBody EventUpdateDTO eventUpdateDTO) {
        eventService.updateEvent(eventUpdateDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}