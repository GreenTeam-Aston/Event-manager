package aston.greenteam.eventmanager.controllers;

import aston.greenteam.eventmanager.dtos.NoticeCategoryDTO;
import aston.greenteam.eventmanager.entities.NoticeCategory;
import aston.greenteam.eventmanager.services.NoticeCategoryService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("notice-categories")
@AllArgsConstructor
public class NoticeCategoryController {

  private final NoticeCategoryService noticeCategoryService;

  @GetMapping("/{id}")
  public ResponseEntity<NoticeCategoryDTO> findById(@PathVariable Long id) {
    return new ResponseEntity<>(noticeCategoryService.findById(id), HttpStatus.OK);
  }

  @GetMapping
  public ResponseEntity<List<NoticeCategoryDTO>> findAll() {
    return new ResponseEntity<>(noticeCategoryService.findAll(), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<NoticeCategory> create(@RequestBody NoticeCategoryDTO noticeCategoryDTO) {
    noticeCategoryService.createNoticeCategory(noticeCategoryDTO);
    return new ResponseEntity<>(noticeCategoryService.createNoticeCategory(noticeCategoryDTO), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public HttpStatus delete(@PathVariable Long id) {
    noticeCategoryService.delete(id);
    return HttpStatus.OK;
  }

}
