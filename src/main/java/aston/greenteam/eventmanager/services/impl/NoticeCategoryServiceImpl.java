package aston.greenteam.eventmanager.services.impl;

import aston.greenteam.eventmanager.dtos.NoticeCategoryDTO;
import aston.greenteam.eventmanager.entities.NoticeCategory;
import aston.greenteam.eventmanager.exceptions.ObjectNotFoundException;
import aston.greenteam.eventmanager.mappers.NoticeCategoryMapper;
import aston.greenteam.eventmanager.repositories.NoticeCategoryRepository;
import aston.greenteam.eventmanager.services.NoticeCategoryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoticeCategoryServiceImpl implements NoticeCategoryService {

  private final NoticeCategoryRepository noticeCategoryRepository;

  private final NoticeCategoryMapper noticeCategoryMapper;

  @Override
  public NoticeCategoryDTO findById(Long id) {
    return noticeCategoryMapper.mapNoticeCategoryToDTO(noticeCategoryRepository.getReferenceById(id));
  }

  @Override
  public List<NoticeCategoryDTO> findAll() {
    return noticeCategoryRepository.findAll().stream()
        .map(noticeCategoryMapper::mapNoticeCategoryToDTO)
        .toList();
  }

  @Override
  public NoticeCategory createNoticeCategory(NoticeCategoryDTO noticeCategoryDTO) {
    NoticeCategory noticeCategory = new NoticeCategory();
    noticeCategory.setTitle(noticeCategoryDTO.getTitle());
    return noticeCategoryRepository.save(noticeCategory);
  }

  @Override
  public void delete(Long id) {
    if(!noticeCategoryRepository.existsById(id)) {
      throw new ObjectNotFoundException("Notice category with id: " + id + " not found");
    }
    noticeCategoryRepository.deleteById(id);
  }
}
