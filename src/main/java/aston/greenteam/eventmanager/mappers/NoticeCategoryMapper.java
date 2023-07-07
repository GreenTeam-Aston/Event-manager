package aston.greenteam.eventmanager.mappers;

import aston.greenteam.eventmanager.dtos.NoticeCategoryDTO;
import aston.greenteam.eventmanager.entities.NoticeCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NoticeCategoryMapper {

    private final NoticeMapper noticeMapper;

    public NoticeCategoryDTO mapNoticeCategoryToDTO(NoticeCategory noticeCategory) {
        return NoticeCategoryDTO.builder()
                .id(noticeCategory.getId())
                .title(noticeCategory.getTitle())
                .build();
    }
}