package aston.greenteam.eventmanager.services;

import aston.greenteam.eventmanager.dtos.ContactDTO;
import aston.greenteam.eventmanager.dtos.EventDTO;
import aston.greenteam.eventmanager.dtos.NoticeDTO;

public interface EmailService {
    void sendNotice(EventDTO eventDTO, ContactDTO contactDTO, String msg);

    void testSend();
}
