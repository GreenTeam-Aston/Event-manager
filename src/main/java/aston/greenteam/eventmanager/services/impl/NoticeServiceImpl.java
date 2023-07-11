package aston.greenteam.eventmanager.services.impl;

import aston.greenteam.eventmanager.dtos.NoticeDTO;
import aston.greenteam.eventmanager.entities.Event;
import aston.greenteam.eventmanager.entities.Notice;
import aston.greenteam.eventmanager.entities.NoticeCategory;
import aston.greenteam.eventmanager.exceptions.ObjectNotFoundException;
import aston.greenteam.eventmanager.mappers.NoticeMapper;
import aston.greenteam.eventmanager.repositories.NoticeRepository;
import aston.greenteam.eventmanager.services.NoticeService;
import aston.greenteam.eventmanager.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepository noticeRepository;

    private final NoticeMapper noticeMapper;

    private final UserService userService;

    @Override
    public NoticeDTO findById(Long id) {
        return noticeMapper.mapNoticeToDTO(noticeRepository.getReferenceById(id));
    }

    @Override
    public List<NoticeDTO> findAll() {
        return noticeRepository.findAll().stream()
                .map(noticeMapper::mapNoticeToDTO)
                .toList();
    }

    @Override
    public List<NoticeDTO> findAllForUserTo(Long idUser) {
        return noticeRepository.findAll().stream()
                .filter(notice -> notice.getUserTo().getId() == idUser)
                .map(noticeMapper::mapNoticeToDTO)
                .toList();
    }

    @Override
    public List<NoticeDTO> findAllForUserFrom(Long idUser) {
        return noticeRepository.findAll().stream()
                .filter(notice -> notice.getUserFrom().getId() == idUser)
                .map(noticeMapper::mapNoticeToDTO)
                .toList();
    }

    @Override
    public List<NoticeDTO> findAllByEvent(Long idEvent) {
        return noticeRepository.findAll().stream()
                .filter(notice -> notice.getEvent().getId() == idEvent)
                .map(noticeMapper::mapNoticeToDTO)
                .toList();
    }

    @Override
    public Notice createNotice(NoticeDTO noticeDTO) {
        NoticeCategory noticeCategory = new NoticeCategory();
        noticeCategory.setId(noticeDTO.getNoticeCategory().getId());
        Event event = new Event();
        event.setId(noticeDTO.getEvent().getId());
        Notice notice = new Notice();
        notice.setMessage(noticeDTO.getMessage());
        notice.setNoticeCategory(noticeCategory);
        notice.setUserFrom(userService.findById(noticeDTO.getUserFrom().getId()));
        notice.setUserTo(userService.findById(noticeDTO.getUserTo().getId()));
        notice.setEvent(event);

        //TODO Слава, здесь нужно прикрутить отправку !!!!

        return noticeRepository.save(notice);
    }

    @Override
    public void delete(Long id) {
        if(!noticeRepository.existsById(id)){
            throw new ObjectNotFoundException("Notice with id: " + id + " not found");
        }
        noticeRepository.deleteById(id);
    }
}