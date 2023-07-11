package aston.greenteam.eventmanager.services.impl;

import aston.greenteam.eventmanager.dtos.ContactDTO;
import aston.greenteam.eventmanager.dtos.NoticeCreateDTO;
import aston.greenteam.eventmanager.dtos.NoticeDTO;
import aston.greenteam.eventmanager.entities.Event;
import aston.greenteam.eventmanager.entities.Notice;
import aston.greenteam.eventmanager.entities.NoticeCategory;
import aston.greenteam.eventmanager.entities.User;
import aston.greenteam.eventmanager.exceptions.ObjectNotFoundException;
import aston.greenteam.eventmanager.mappers.ContactMapper;
import aston.greenteam.eventmanager.mappers.NoticeMapper;
import aston.greenteam.eventmanager.repositories.EventRepository;
import aston.greenteam.eventmanager.repositories.NoticeCategoryRepository;
import aston.greenteam.eventmanager.repositories.NoticeRepository;
import aston.greenteam.eventmanager.services.EmailService;
import aston.greenteam.eventmanager.services.NoticeService;
import aston.greenteam.eventmanager.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepository noticeRepository;

    private final EventRepository eventRepository;

    private final NoticeCategoryRepository noticeCategoryRepository;

    private final NoticeMapper noticeMapper;

    private final UserService userService;

    private final EmailService emailService;

    private final ContactMapper contactMapper;

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
    public NoticeDTO createNotice(NoticeCreateDTO noticeCreateDTO) {
        Notice notice = fieldsNotice(noticeCreateDTO);
        ContactDTO contactDTO = contactMapper.mapContactToDTO(notice.getUserTo().getContact());
        NoticeDTO noticeDTO = noticeMapper.mapNoticeToDTO(notice);
        emailService.sendNotice(noticeDTO.getEvent(), contactDTO, noticeDTO.getMessage());
        noticeRepository.save(notice);
        return noticeDTO;
    }

    @Override
    public void delete(Long id) {
        if (!noticeRepository.existsById(id)) {
            throw new ObjectNotFoundException("Уведомление по ид: " + id + " не найдено.");
        }
        noticeRepository.deleteById(id);
    }

    private Notice fieldsNotice(NoticeCreateDTO noticeCreateDTO) {
        NoticeCategory noticeCategory = noticeCategoryRepository
                .findById(noticeCreateDTO.getNoticeCategoryId())
                .orElseThrow(() -> new ObjectNotFoundException("Not found notice category with id: " +
                        noticeCreateDTO.getNoticeCategoryId()));
        User userTo = userService.findById(noticeCreateDTO.getUserToId());
        User userFrom = userService.findById(noticeCreateDTO.getUserFromId());
        Event event = eventRepository.findById(noticeCreateDTO.getEventId()).orElseThrow(
                () -> new ObjectNotFoundException("Event with id:" + noticeCreateDTO.getEventId() + " not found."));
        Notice notice = new Notice();
        notice.setMessage(noticeCreateDTO.getMsg());
        notice.setNoticeCategory(noticeCategory);
        notice.setUserTo(userTo);
        notice.setUserFrom(userFrom);
        notice.setEvent(event);
        return notice;
    }
}
