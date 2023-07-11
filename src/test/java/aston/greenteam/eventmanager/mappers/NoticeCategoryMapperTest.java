package aston.greenteam.eventmanager.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import aston.greenteam.eventmanager.dtos.NoticeCategoryDTO;
import aston.greenteam.eventmanager.entities.NoticeCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = NoticeCategoryMapper.class)
public class NoticeCategoryMapperTest {

  private NoticeCategory noticeCategory;
  private NoticeCategoryDTO noticeCategoryDTO;

  @Autowired
  private NoticeCategoryMapper noticeCategoryMapper;

  @BeforeEach
  void initialize() {
    noticeCategory = new NoticeCategory();
    noticeCategory.setTitle("Test");
    noticeCategoryDTO = noticeCategoryMapper.mapNoticeCategoryToDTO(noticeCategory);
  }

  @Test
  @DisplayName("mapNoticeCategoryToDTOTest")
  void mapNoticeCategoryToDTOTest() {
    NoticeCategoryDTO result = noticeCategoryMapper.mapNoticeCategoryToDTO(noticeCategory);
    assertEquals(noticeCategoryDTO, result);
  }
}
