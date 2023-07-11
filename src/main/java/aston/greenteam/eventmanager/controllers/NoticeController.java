package aston.greenteam.eventmanager.controllers;

import aston.greenteam.eventmanager.dtos.NoticeDTO;
import aston.greenteam.eventmanager.entities.Notice;
import aston.greenteam.eventmanager.services.NoticeService;
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
@RequestMapping("notice")
@AllArgsConstructor
public class NoticeController {

  private final NoticeService noticeService;

  @GetMapping("/{id}")
  public ResponseEntity<NoticeDTO> findById(@PathVariable Long id) {
    return new ResponseEntity<>(noticeService.findById(id), HttpStatus.OK);
  }

  @GetMapping
  public ResponseEntity<List<NoticeDTO>> findAll() {
    return new ResponseEntity<>(noticeService.findAll(), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<Notice> create(@RequestBody NoticeDTO noticeDTO) {
    return new ResponseEntity<>(noticeService.createNotice(noticeDTO), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public HttpStatus delete(@PathVariable Long id) {
    noticeService.delete(id);
    return HttpStatus.OK;
  }
}
