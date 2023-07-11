package aston.greenteam.eventmanager.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import aston.greenteam.eventmanager.dtos.NoticeCategoryDTO;
import aston.greenteam.eventmanager.dtos.NoticeDTO;
import aston.greenteam.eventmanager.entities.Notice;
import aston.greenteam.eventmanager.entities.NoticeCategory;
import aston.greenteam.eventmanager.services.NoticeCategoryService;
import aston.greenteam.eventmanager.services.NoticeService;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

@SpringBootTest(classes = NoticeController.class)
public class NoticeControllerTest {
  private Long id;
  private NoticeDTO noticeDTO;
  private ResponseEntity<List<NoticeDTO>> noticeDTOListResponseEntity;
  private ResponseEntity<NoticeDTO> noticeDTOResponseEntity;
  private ResponseEntity<Notice> noticeResponseEntity;
  private Notice notice;

  @Autowired
  @InjectMocks
  private NoticeController noticeController;

  @MockBean
  private NoticeService noticeService;

  @BeforeEach
  void initialize(){
    Random random = new Random();
    id = random.nextLong();
    notice = new Notice();
    notice.setId(id);
    notice.setMessage("Test");
    noticeDTO = new NoticeDTO();
    noticeDTO.setId(notice.getId());
    noticeDTO.setMessage(notice.getMessage());
    noticeDTOResponseEntity = ResponseEntity.ok(noticeDTO);
    noticeResponseEntity = ResponseEntity.ok(notice);
    noticeDTOListResponseEntity = ResponseEntity.ok(List.of(noticeDTO));
  }

  @Test
  @DisplayName("findByIdTest")
  void findByIdTest(){
    when(noticeService.findById(id))
        .thenReturn(noticeDTO);
    ResponseEntity<NoticeDTO> result = noticeController.findById(id);
    assertEquals(noticeDTOResponseEntity, result);
  }

  @Test
  @DisplayName("findAllTest")
  void findAllTest(){
    when(noticeService.findAll())
        .thenReturn(List.of(noticeDTO));
    ResponseEntity<List<NoticeDTO>> result = noticeController.findAll();
    assertEquals(noticeDTOListResponseEntity, result);
  }

  @Test
  @DisplayName("createTest")
  void createTest(){
    when(noticeService.createNotice(noticeDTO))
        .thenReturn(notice);
    ResponseEntity<Notice> result = noticeController.create(noticeDTO);
    assertEquals(noticeResponseEntity, result);
  }
}
