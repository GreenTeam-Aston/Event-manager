package aston.greenteam.eventmanager.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import aston.greenteam.eventmanager.dtos.NoticeCategoryDTO;
import aston.greenteam.eventmanager.entities.Notice;
import aston.greenteam.eventmanager.entities.NoticeCategory;
import aston.greenteam.eventmanager.services.NoticeCategoryService;
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

@SpringBootTest(classes = NoticeCategoryController.class)
public class NoticeCategoryControllerTest {

  private Long id;
  private NoticeCategoryDTO noticeCategoryDTO;
  private ResponseEntity<List<NoticeCategoryDTO>> noticeCategoryDTOListResponseEntity;
  private ResponseEntity<NoticeCategoryDTO> noticeCategoryDTOResponseEntity;
  private ResponseEntity<NoticeCategory> noticeCategoryResponseEntity;
  private NoticeCategory noticeCategory;

  @Autowired
  @InjectMocks
  private NoticeCategoryController noticeCategoryController;

  @MockBean
  private NoticeCategoryService noticeCategoryService;

  @BeforeEach
  void initialize(){
    Random random = new Random();
    id = random.nextLong();
    noticeCategory = new NoticeCategory();
    noticeCategory.setTitle("Test");
    noticeCategoryDTO = new NoticeCategoryDTO();
    noticeCategoryDTO.setTitle(noticeCategory.getTitle());
    noticeCategoryDTOResponseEntity = ResponseEntity.ok(noticeCategoryDTO);
    noticeCategoryResponseEntity = ResponseEntity.ok(noticeCategory);
    noticeCategoryDTOListResponseEntity = ResponseEntity.ok(List.of(noticeCategoryDTO));
  }

  @Test
  @DisplayName("findByIdTest")
  void findByIdTest(){
    when(noticeCategoryService.findById(id))
        .thenReturn(noticeCategoryDTO);
    ResponseEntity<NoticeCategoryDTO> result = noticeCategoryController.findById(id);
    assertEquals(noticeCategoryDTOResponseEntity, result);
  }

  @Test
  @DisplayName("findAllTest")
  void findAllTest(){
    when(noticeCategoryService.findAll())
        .thenReturn(List.of(noticeCategoryDTO));
    ResponseEntity<List<NoticeCategoryDTO>> result = noticeCategoryController.findAll();
    assertEquals(noticeCategoryDTOListResponseEntity, result);
  }

  @Test
  @DisplayName("createTest")
  void createTest(){
    when(noticeCategoryService.createNoticeCategory(noticeCategoryDTO))
        .thenReturn(noticeCategory);
    ResponseEntity<NoticeCategory> result = noticeCategoryController.create(noticeCategoryDTO);
    assertEquals(noticeCategoryResponseEntity, result);
  }
}
