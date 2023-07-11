package aston.greenteam.eventmanager.services;

import aston.greenteam.eventmanager.dtos.ContactDTO;
import aston.greenteam.eventmanager.dtos.EventDTO;
import aston.greenteam.eventmanager.dtos.WeatherDTO;

public interface EmailService {
    void sendNotice(EventDTO eventDTO, ContactDTO contactDTO, String msg);

    void sendMessage(WeatherDTO weatherDTO, Long userId);

    void testSend();
}
