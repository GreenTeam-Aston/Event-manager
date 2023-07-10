package aston.greenteam.eventmanager.services.impl;

import aston.greenteam.eventmanager.dtos.MessageDTO;
import aston.greenteam.eventmanager.services.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    @Value("${spring.mail.sender.email}")
    private String senderAddress;
    private final JavaMailSender javaMailSender;

    @Override
    public void sendMessage(MessageDTO messageDTO, Long userId) {

    }

    @Override
    public void testSend() {
        String email = "steve128@yandex.ru";
        String msg = "testMessage";
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(senderAddress);
        message.setTo(email);
        message.setSubject(msg);
        message.setText(msg);
        javaMailSender.send(message);
    }


}
