package aston.greenteam.eventmanager.services;

import aston.greenteam.eventmanager.dtos.NoticeDTO;
import aston.greenteam.eventmanager.entities.Notice;
import java.util.List;

public interface NoticeService {
    NoticeDTO findById(Long id);
    List<NoticeDTO> findAll();
    List<NoticeDTO> findAllForUserTo(Long idUser);
    List<NoticeDTO> findAllForUserFrom(Long idUser);
    List<NoticeDTO> findAllByEvent(Long idEvent);
    Notice createNotice(NoticeDTO noticeDTO);
    void delete(Long id);
}
