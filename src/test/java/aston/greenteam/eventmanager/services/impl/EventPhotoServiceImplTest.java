package aston.greenteam.eventmanager.services.impl;

import aston.greenteam.eventmanager.dtos.EventPhotoPostDto;
import aston.greenteam.eventmanager.entities.Event;
import aston.greenteam.eventmanager.entities.EventPhoto;
import aston.greenteam.eventmanager.entities.User;
import aston.greenteam.eventmanager.exceptions.EventNotFoundException;
import aston.greenteam.eventmanager.exceptions.FileWasNotSavedException;
import aston.greenteam.eventmanager.repositories.EventPhotoRepository;
import aston.greenteam.eventmanager.repositories.EventRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
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
    private final String photoUri = "src/main/resources/photos/Albert_Einstein_Head.jpg";
    private FileSystemResource photoAsFile;
    private MockMultipartFile existedFile;
    private MockMultipartFile newFile;
    @Mock
    private EventPhotoRepository photoRepository;
    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private EventPhotoServiceImpl eventPhotoService;


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
=
}