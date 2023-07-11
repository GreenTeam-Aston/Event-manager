package aston.greenteam.eventmanager.controllers;

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

    @GetMapping("/test")
    public ResponseEntity<?> testSend(){
        emailService.testSend();
        return ResponseEntity.ok(HttpStatus.OK);
    }




}
