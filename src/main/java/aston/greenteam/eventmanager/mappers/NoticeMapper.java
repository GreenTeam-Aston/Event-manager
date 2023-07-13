package aston.greenteam.eventmanager.mappers;

import aston.greenteam.eventmanager.dtos.NoticeDTO;
import aston.greenteam.eventmanager.entities.Notice;
import aston.greenteam.eventmanager.services.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NoticeMapper {

    private final NoticeCategoryMapper noticeCategoryMapper;
    private final EventService eventService;
    private final UserMapper userMapper;

    public NoticeDTO mapNoticeToDTO(Notice notice) {
        return NoticeDTO.builder()
                .id(notice.getId())
                .message(notice.getMessage())
                .noticeCategory(noticeCategoryMapper.mapNoticeCategoryToDTO(notice.getNoticeCategory()))
                .userFrom(userMapper.mapUserToSimpleDTO(notice.getUserFrom()))
                .userTo(userMapper.mapUserToSimpleDTO(notice.getUserTo()))
                .event(eventService.toDTO(notice.getEvent()))
                .build();
    }
}