package aston.greenteam.eventmanager.controllers;

import aston.greenteam.eventmanager.dtos.EventDTO;
import aston.greenteam.eventmanager.services.EventService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class EventControllerTest {
    @Mock
    EventService eventService;
    @InjectMocks
    EventController eventController;
    private EventDTO eventDTO;
    private MockMvc mvc;
    private final ObjectMapper mapper = JsonMapper.builder()
            .addModule(new JavaTimeModule())
            .build();

    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders
                .standaloneSetup(eventController)
                .build();
        eventDTO = EventDTO.builder()
                .id(1L)
                .title("title")
                .description("description")
                .tags("tags")
                .build(); //
    }

    @SneakyThrows
    @Test
    void getAllEvents() {
        when(eventService.findAll()).thenReturn(List.of(eventDTO));
        mvc.perform(get("/api/v1/events"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(eventDTO.getId()), Long.class))
                .andExpect(jsonPath("$[0].title", is(eventDTO.getTitle())))
                .andExpect(jsonPath("$[0].description", is(eventDTO.getDescription())))
                .andExpect(jsonPath("$[0].tags", is(eventDTO.getTags())));
    }

    @SneakyThrows
    @Test
    void getById() {
        when(eventService.findById(anyLong()))
                .thenReturn(eventDTO);
        mvc.perform(get("/api/v1/events/{id}", eventDTO.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(eventDTO.getId()), Long.class))
                .andExpect(jsonPath("$.title", is(eventDTO.getTitle())))
                .andExpect(jsonPath("$.description", is(eventDTO.getDescription())))
                .andExpect(jsonPath("$.tags", is(eventDTO.getTags())));
    }

    @SneakyThrows
    @Test
    void removeEventById() {
        doNothing().when(eventService).deleteEventById(eventDTO.getId());
        mvc.perform(delete("/api/v1/events/{id}", eventDTO.getId()))
                .andExpect(status().isOk());
        Mockito.verify(eventService, Mockito.times(1))
                .deleteEventById(eventDTO.getId());
    }

    @SneakyThrows
    @Test
    void findAllEventsByTags() {
        when(eventService.findAllByTag(anyString())).thenReturn(List.of(eventDTO));
        mvc.perform(get("/api/v1/events/tag/{tag}", eventDTO.getTags()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(eventDTO.getId()), Long.class))
                .andExpect(jsonPath("$[0].title", is(eventDTO.getTitle())))
                .andExpect(jsonPath("$[0].description", is(eventDTO.getDescription())))
                .andExpect(jsonPath("$[0].tags", is(eventDTO.getTags())));

    }

    @SneakyThrows
    @Test
    void updateEvent() {
        when(eventService.updateEvent(any(), anyLong())).thenReturn(eventDTO);
        mvc.perform(put("/api/v1/events/{id}", eventDTO.getId())
                        .content(mapper.writeValueAsString(eventDTO))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(eventDTO.getId()), Long.class))
                .andExpect(jsonPath("$[0].title", is(eventDTO.getTitle())))
                .andExpect(jsonPath("$[0].description", is(eventDTO.getDescription())))
                .andExpect(jsonPath("$[0].tags", is(eventDTO.getTags())));
    }
}