package aston.greenteam.eventmanager.mappers;

import aston.greenteam.eventmanager.dtos.NoticeDTO;
import aston.greenteam.eventmanager.entities.Notice;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NoticeMapper {

    private final NoticeCategoryMapper noticeCategoryMapper;
    private final UserMapper userMapper;
    private final EventMapper eventMapper;


    public NoticeDTO mapNoticeToDTO(Notice notice) {
        return NoticeDTO.builder()
                .id(notice.getId())
                .message(notice.getMessage())
                .noticeCategory(noticeCategoryMapper.mapNoticeCategoryToDTO(notice.getNoticeCategory()))
                .userFrom(userMapper.mapUserToDTO(notice.getUserFrom()))
                .userTo(userMapper.mapUserToDTO(notice.getUserTo()))
                .event(eventMapper.mapEventToDTO(notice.getEvent(), null, null, null, null))
                .build();
    }
}