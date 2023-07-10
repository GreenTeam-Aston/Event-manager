package aston.greenteam.eventmanager.services.impl;

import aston.greenteam.eventmanager.dtos.EventPhotoPostDto;
import aston.greenteam.eventmanager.entities.Event;
import aston.greenteam.eventmanager.entities.EventPhoto;
import aston.greenteam.eventmanager.entities.User;
import aston.greenteam.eventmanager.exceptions.EventNotFoundException;
import aston.greenteam.eventmanager.exceptions.FileWasNotSavedException;
import aston.greenteam.eventmanager.repositories.EventPhotoRepository;
import aston.greenteam.eventmanager.repositories.EventRepository;
import jdk.jfr.ContentType;
import org.hibernate.query.PathException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EventPhotoServiceImplTest {

    private EventPhoto eventPhoto;
    private Event event;
    private String photoUri = "src/main/resources/photos/Albert_Einstein_Head.jpg";
    private FileSystemResource photoAsFile;
    private MockMultipartFile existedFile;
    private MockMultipartFile newFile;
    @Mock
    private EventPhotoRepository photoRepository;
    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private EventPhotoServiceImpl eventPhotoService;

    @BeforeEach
    void setUp() {
        LocalDateTime createdAt = LocalDateTime.now().minusDays(2);
        photoAsFile = new FileSystemResource("src/main/resources/photos/Albert_Einstein_Head.jpg");
        event = new Event(1L, "EventTitle",
                "EventDescription",
                LocalDateTime.now().minusDays(1),
                LocalDateTime.now().plusDays(1),
                "chto",
                "chto",
                BigDecimal.valueOf(1),
                createdAt,
                createdAt,
                true,
                new User(),
                "tag",
                new ArrayList<>(),
                new ArrayList<>());
        eventPhoto = EventPhoto.builder().photoUri(photoUri).event(event).id(1L).build();
    }

    @Test
    void getPhoto_ShouldReturnPhotoWithoutMistake() {
        when(photoRepository.findById(anyLong()))
                .thenReturn(Optional.ofNullable(eventPhoto));
        FileSystemResource expectedResource = eventPhotoService.getPhoto(1L);
        assertEquals(expectedResource.getPath(), photoAsFile.getPath());
        verify(photoRepository, times(1)).findById(1L);
    }

    @Test
    void getPhoto_ShouldThrowException() {
        doThrow(EventNotFoundException.class).when(photoRepository).findById(any());
        assertThrows(EventNotFoundException.class, () -> eventPhotoService.getPhoto(1L));
    }

    @Test
    void addEventPhotoWhenUrlIsWrongShouldThrowException() {
        byte[] array = {0};
        MockMultipartFile mockMultipartFile = new MockMultipartFile("no", array);
        EventPhotoPostDto dto = EventPhotoPostDto.builder().photoUri(photoUri).id(1L).build();
        when(eventRepository.findById(any()))
                .thenReturn(Optional.ofNullable(event));
        assertThrows(FileWasNotSavedException.class,
                () -> eventPhotoService.addEventPhoto(1L, mockMultipartFile));
        verify(eventRepository, times(1)).findById(1L);
    }

    @Test
    void addEventPhotoWhenUrlIsOk() throws IOException {
        EventPhotoPostDto expected = EventPhotoPostDto.builder().photoUri(photoUri).id(1L).build();
        newFile = new MockMultipartFile(photoUri, "Albert_Einstein_Head.jpg",
                "image", photoAsFile.getContentAsByteArray());

        when(eventRepository.findById(any()))
                .thenReturn(Optional.ofNullable(event));

        assertThrows(FileWasNotSavedException.class, () -> eventPhotoService.addEventPhoto(1L, newFile));
        verify(eventRepository, times(1)).findById(1L);
    }

}