package aston.greenteam.eventmanager.services;


import aston.greenteam.eventmanager.dtos.NoticeCategoryDTO;
import aston.greenteam.eventmanager.entities.NoticeCategory;
import java.util.List;

public interface NoticeCategoryService {
  NoticeCategoryDTO findById(Long id);
  List<NoticeCategoryDTO> findAll();
  NoticeCategory createNoticeCategory(NoticeCategoryDTO noticeCategoryDTO);
  void delete(Long id);
}
