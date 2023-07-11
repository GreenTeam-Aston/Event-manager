package aston.greenteam.eventmanager.controllers;

import aston.greenteam.eventmanager.dtos.NoticeCreateDTO;
import aston.greenteam.eventmanager.dtos.NoticeDTO;
import aston.greenteam.eventmanager.services.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
}
