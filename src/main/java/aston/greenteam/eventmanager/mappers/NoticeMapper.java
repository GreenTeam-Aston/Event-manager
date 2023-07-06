package aston.greenteam.eventmanager.mappers;

import aston.greenteam.eventmanager.dtos.NoticeDTO;
import aston.greenteam.eventmanager.entities.Notice;
import aston.greenteam.eventmanager.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NoticeMapper {

    private final NoticeCategoryMapper noticeCategoryMapper;
    private final UserService userService;
    private final EventMapper eventMapper;

    public NoticeDTO mapNoticeToDTO(Notice notice) {
        return NoticeDTO.builder()
                .id(notice.getId())
                .message(notice.getMessage())
                .noticeCategory(noticeCategoryMapper.mapNoticeCategoryToDTO(notice.getNoticeCategory()))
                .userFrom(userService.userToDTO(notice.getUserFrom()))
                .userTo(userService.userToDTO(notice.getUserTo()))
                .event(eventMapper.mapEventToDTO(notice.getEvent()))
                .build();
    }
}