package aston.greenteam.eventmanager.controllers;

import aston.greenteam.eventmanager.dtos.MessageDTO;
import aston.greenteam.eventmanager.services.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/mailing")
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;
    @PostMapping("/send/{userId}")
    public ResponseEntity<?> sendMessage(@RequestBody MessageDTO messageDTO, @PathVariable Long userId){
        emailService.sendMessage(messageDTO, userId);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/test")
    public ResponseEntity<?> testSend(){
        emailService.testSend();
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
