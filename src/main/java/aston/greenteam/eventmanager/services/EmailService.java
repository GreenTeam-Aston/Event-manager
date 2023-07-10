package aston.greenteam.eventmanager.services;

import aston.greenteam.eventmanager.dtos.MessageDTO;

public interface EmailService {
    void sendMessage(MessageDTO messageDTO, Long userId);

    void testSend();
}
