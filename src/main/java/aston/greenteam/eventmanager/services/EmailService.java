package aston.greenteam.eventmanager.services;

import aston.greenteam.eventmanager.dtos.MessageDTO;
import aston.greenteam.eventmanager.dtos.WeatherDTO;

public interface EmailService {
    void sendMessage(MessageDTO messageDTO, Long userId);

    void sendMessage(WeatherDTO weatherDTO, Long userId);

    void testSend();
}