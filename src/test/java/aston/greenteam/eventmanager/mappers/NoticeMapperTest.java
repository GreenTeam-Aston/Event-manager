package aston.greenteam.eventmanager.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import aston.greenteam.eventmanager.dtos.NoticeDTO;
import aston.greenteam.eventmanager.entities.Event;
import aston.greenteam.eventmanager.entities.Notice;
import aston.greenteam.eventmanager.entities.NoticeCategory;
import aston.greenteam.eventmanager.entities.User;
import aston.greenteam.eventmanager.services.EventService;
import aston.greenteam.eventmanager.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest(classes = NoticeMapper.class)
public class NoticeMapperTest {
  private Notice notice;
  private NoticeDTO noticeDTO;

  @Autowired
  private NoticeMapper noticeMapper;

  @MockBean
  private NoticeCategoryMapper noticeCategoryMapper;

  @MockBean
  private UserMapper userMapper;

  @MockBean
  private EventMapper eventMapper;

  @MockBean
  private NoticeCategory noticeCategory;

  @MockBean
  private Event event;

  @MockBean
  private User user;

  @BeforeEach
  void initialize() {
    noticeMapper = new NoticeMapper(noticeCategoryMapper, userMapper, eventMapper);
    notice = new Notice();
    notice.setNoticeCategory(noticeCategory);
    notice.setEvent(event);
    notice.setUserTo(user);
    notice.setUserFrom(user);
    notice.setMessage("Test");
    noticeDTO = noticeMapper.mapNoticeToDTO(notice);
  }

  @Test
  @DisplayName("mapNoticeToDTOTest")
  void mapNoticeToDTOTest() {
    NoticeDTO result = noticeMapper.mapNoticeToDTO(notice);
    assertEquals(noticeDTO, result);
  }
}
