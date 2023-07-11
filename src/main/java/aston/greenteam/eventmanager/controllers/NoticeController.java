package aston.greenteam.eventmanager.controllers;
import aston.greenteam.eventmanager.dtos.NoticeCreateDTO;
import aston.greenteam.eventmanager.dtos.NoticeDTO;
import aston.greenteam.eventmanager.services.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/notices")
public class NoticeController {

    private final NoticeService noticeService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public NoticeDTO createNoticeDTO(@RequestBody NoticeCreateDTO noticeCreateDTO) {
        return noticeService.createNotice(noticeCreateDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NoticeDTO> findById(@PathVariable Long id) {
        return new ResponseEntity<>(noticeService.findById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<NoticeDTO>> findAll() {
        return new ResponseEntity<>(noticeService.findAll(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable Long id) {
        noticeService.delete(id);
        return HttpStatus.OK;
    }
}
